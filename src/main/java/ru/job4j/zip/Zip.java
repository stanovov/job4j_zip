package ru.job4j.zip;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private String directory;

    private String output;

    private String exclude;

    private Path target;

    private List<Path> sources;

    public Zip(String[] args) throws IOException {
        validate(args);
        init();
    }

    public boolean packFiles() {
        boolean rsl = true;
        Path pathBase = target.getParent();
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                Path pathRelative = pathBase.relativize(source);
                zip.putNextEntry(new ZipEntry(pathRelative.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            rsl = false;
            e.printStackTrace();
        }
        return rsl;
    }

    private void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Incorrect number of parameters. Usage \"java -jar dir.jar "
                            + "ROOT_FOLDER EXCLUDING_EXTENSIONS ARCHIVE_NAME\"."
            );
        }
        ArgsName argsName = ArgsName.of(args);
        directory = argsName.get("d");
        output = argsName.get("o");
        exclude = argsName.get("e");
        File file = new File(directory);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    private void init() throws IOException {
        Path start = Paths.get(directory);
        target = Paths.get(start.getParent() + File.separator + output);
        sources = Search.search(start, getPredicate(exclude));
    }

    private static Predicate<Path> getPredicate(String exclude) {
        Predicate<Path> predicate;
        if (exclude.isEmpty()) {
            predicate = p -> true;
        } else {
            String[] excludes = exclude.split(",");
            if (excludes.length == 1) {
                predicate = p -> !p.toFile().getName().endsWith(exclude);
            } else {
                Set<String> extensions = new HashSet<>(
                        Arrays.asList(excludes)
                );
                predicate = p -> {
                    String fileName = p.toFile().getName();
                    return extensions.stream()
                            .noneMatch(fileName::endsWith);
                };
            }
        }
        return predicate;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip(args);
        if (zip.packFiles()) {
            System.out.println("Archiving completed");
        }
    }
}
