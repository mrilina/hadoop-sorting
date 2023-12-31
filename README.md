# Сервис реализации механизма сортировки большого объема данных
Разработанный сервис позволяет производить хранение и сортировку данных большого объема.
В своей осное испльзует Hadoop – свободно распространяемый набор утилит, библиотек и 
фреймворк для разработки и выполнения распределённых программ, работающих на кластерах из сотен и тысяч узлов.

## Общая информация и структура проекта

Внешние проектные ресурсы:

- пакет утилит, библиотек и фреймворков Apache Hadoop для работы с Big Data;
- библиотека Htuple Core для работы с кортежами;
- библиотеки для тестирования MRUnit и JUnit: 
  - JUnit представляет собой фреймворк для автоматического юнит-тестирования программ;
  - MRUnit основан на JUnit и позволяет проводить модульное тестирование преобразователей, преобразователей и некоторых ограниченных интеграционных тестов взаимодействия преобразователя и преобразователя вместе с объединителями, 
настраиваемыми счетчиками и разделителями.

Сервис построен на Java SE 17.

Структурно, проект разделен на следующие модули:
- реализация сортировки на основе нескольких полей, составляющих композитный ключ
  - пример реализован на предметной области картотека книг
  - в исходном файле содержится набор строк вида {Номер полки:Наименование:Автор:Год издания:Количество страниц}
  - каждый следующий атрибут разделен символом двоеточие :
  - сортировка осуществляется по 2 полям: по году издания и по автору
  - выходной файл содержит строки вида {Карточка книги [книжная полка = 12390, наименование = 'Коппола. Семья, изменившая кинематограф', автор = 'Натан Айан', год издания = 2023, количество страниц = 448]}
- реализация сортировки, основанная на использовании структуры Tuple
  - сортировка абстрактных полей, не привязанных к доменной области, реализуется на основе использования структуры Tuple (org.htuple.Tuple)
  - В исходном файле содержится набор строк вида {определение:значение}
  - каждый следующий атрибут разделен символом двоеточие :
  - сортировка осуществляется сначала по определению, затем по значению
- тестирование

Разделение по пакетам:
- `compositekey` в пакете содержится исходный код реализация сортировки по нескольким полям кортежей:
  - `BookReviewDriver` - основной класс для запуска сервиса;
  - `BookMapper` - класс, отвечающий за модуль сопоставления. Принимает входные данные, анализирует их и передает кортежи;
  - `BookReducer` - класс редуктор, принимает кортежи, сформированные в модуле сопоставления, выполняет операцию сводки, которая создает результат меньшего размера, объединяющий данные модуля сопоставления.
  - `BookReviewVO` - класс, определяющий представление данных, чтение/запись из входного/выходного потока.
  - `CustomBookKey` - класс, определяющий ключи и порядок сортировки;
  - `BookGroupComparator` - класс, определяющий порядок сортировки ключей;
  - `CustomBookPartitioner` - реализация бизнес-логики. 
- `tuplekey` в пакете содержится исходный код реализация сортировки с использованием структуры Tuple: 
  - `TupleSortingDriver` - основной класс для запуска сервиса;
  - `TupleSortingFields` - класс, представляющий собой enum, определяет наименования для обращения со структурой Tuple;
  - `TupleSortingMap` - класс, отвечающий за модуль сопоставления. Принимает входные данные, анализирует их и передает кортежи; 
  - `TupleSortingReduce` - класс редуктор, принимает кортежи, сформированные в модуле сопоставления, выполняет операцию сводки, которая создает результат меньшего размера, объединяющий данные модуля сопоставления.
- `utils` в пакете содержатся вспомогательные классы и утилиты.
- `test` в пакете содержатся unit тесты.

## Сборка сервиса
### Используемое ПО
Для развертывания и сборки компонент сервиса на компьютере должны быть предустановлены:
1. Java SDK ver. 17+
2. maven ver. 3.8+

Перед сборкой убедитесь что переменная окружения JAVA_HOME указывает на корневой каталог Java SDK 17-й версии.

В корневом каталоге проекта необходимо выполнить команду:
```shell
  mvn install
```
В выходной директории target появятся 2 файла: hadoop-sorting-1.0-SNAPSHOT-shaded.jar и hadoop-sorting-1.0-SNAPSHOT.jar

Для запуска программы необходимо выполнить команду в формате 
```shell
  java -cp <путь к jar файлу> <полное имя Main класса> <путь к файлу с исходными данными> <путь к выходной папке>
```

```shell
Пример запуска программы на выполнение с использованием композитного ключа, входной файл books.data, пример описан ниже

java -cp hadoop-sorting-1.0-SNAPSHOT-shaded.jar com.hadoop.sorting.compositekey.BookReviewDriver books.data /home/iilina/projects/tests/output

java -cp hadoop-sorting-1.0-SNAPSHOT-shaded.jar com.hadoop.sorting.tuplekey.TupleSortingDriver names.data output
```

```shell
/** Пример входного потока данных для сортировки по составному ключу (books.data). */

31852:Война и мир:Толстой Л.Н.:1996:1300
31922:Преступление и наказание:Достоевский Ф.М:2022:672
32122:Приключения Буратино, или Золотой Ключик:Толстой А.Н.:2020:128
32122:Зачем нужна геология. Краткая история прошлого и будущего нашей планеты:Макдугалл Дуг:2022:400
12390:Коппола. Семья, изменившая кинематограф:Натан Айан:2023:448
31852:Беседы о русской культуре. Быт и традиции русского дворянства (XVIII - начало XIX века):Лотман Ю.М.:2023:560
31922:Король и Шут. Старая книга:Князев А.С.:2018:280

/** Пример выходного файла. */
/** Сортировка произведена сначала по году издания, затем по автору в порядке возрастания. */

 Карточка книги [книжная полка = 31852, наименование = 'Война и мир', автор = 'Толстой Л.Н.', год издания = 1996, количество страниц = 1300]
 Карточка книги [книжная полка = 31922, наименование = 'Король и Шут. Старая книга', автор = 'Князев А.С.', год издания = 2018, количество страниц = 280]
 Карточка книги [книжная полка = 32122, наименование = 'Приключения Буратино, или Золотой Ключик', автор = 'Толстой А.Н.', год издания = 2020, количество страниц = 128]
 Карточка книги [книжная полка = 31922, наименование = 'Преступление и наказание', автор = 'Достоевский Ф.М', год издания = 2022, количество страниц = 672]
 Карточка книги [книжная полка = 32122, наименование = 'Зачем нужна геология. Краткая история прошлого и будущего нашей планеты', автор = 'Макдугалл Дуг', год издания = 2022, количество страниц = 400]
 Карточка книги [книжная полка = 31852, наименование = 'Беседы о русской культуре. Быт и традиции русского дворянства (XVIII - начало XIX века)', автор = 'Лотман Ю.М.', год издания = 2023, количество страниц = 560]
 Карточка книги [книжная полка = 12390, наименование = 'Коппола. Семья, изменившая кинематограф', автор = 'Натан Айан', год издания = 2023, количество страниц = 448]

/** Пример входного потока данных для сортировки с использованием структуры Tuple (names.data). */
Петров:Сергей
Петров:Александр
Анохин:Александр

/** Пример выходного файла. */
/** Сортировка произведена сначала по имени, затем по фамилии в порядке возрастания. */

Анохин:Александр
Петров:Александр
Петров:Сергей
```

