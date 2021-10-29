[![Build Status](https://travis-ci.com/stanovov/job4j_zip.svg?branch=master)](https://travis-ci.com/stanovov/job4j_zip)
[![codecov](https://codecov.io/gh/stanovov/job4j_zip/branch/master/graph/badge.svg)](https://codecov.io/gh/stanovov/job4j_zip)

![](https://img.shields.io/badge/Maven-=_3-red)
![](https://img.shields.io/badge/Java-=_14-orange)
![](https://img.shields.io/badge/JUnit-=_4-yellowgreen)
![](https://img.shields.io/badge/JaCoCo-c75a28)
![](https://img.shields.io/badge/Checkstyle-lightgrey)

# job4j_zip

+ [Об утилите](#0б-утилите)
+ [Запуск и сборка](#Сборка-и-запуск)
+ [Использование](#Использование)
+ [Контакты](#Контакты)

## О проекте

Данная утилита умеет архивировать папки с вложенными в неё файлами.

## Сборка и запуск

### Запуск через терминал

1.Собрать jar через Maven

`mvn install -Dmaven.test-skip=true`

2.Запустить jar файл

`java -jar target/pack.jar`

### Запуск через IDE

Перейти к папке `src/main/java` и файлу `ru.job4j.zip.Zip`

## Использование

1. При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\
2. В качестве ключа передается расширения файлов, которые не нужно включать в архив.
3. Запуск проекта:

`java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip`

+ java -jar pack.jar - Это собранный jar.
+ **-d** - directory - которую мы хотим архивировать
+ **-e** - exclude - исключить файлы *.xml
+ **-o** - output - во что мы архивируем.

## Контакты

Становов Семён Сергеевич

Email: sestanovov@gmail.com

Telegram: [@stanovovss](https://t.me/stanovovss)