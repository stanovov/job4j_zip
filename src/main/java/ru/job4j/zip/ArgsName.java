package ru.job4j.zip;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Key \"%s\" not found", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Incorrect pair format. "
                        + "The entry for the parameter must begin with a sign \"-\".");
            }
            String[] entry = arg.split("=");
            if (entry.length != 2) {
                throw new IllegalArgumentException("Incorrect pair format. "
                        + "Keys and values must be separated by an equal sign.");
            }
            values.put(entry[0].substring(1), entry[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

}
