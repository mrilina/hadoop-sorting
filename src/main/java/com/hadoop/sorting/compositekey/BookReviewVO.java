package com.hadoop.sorting.compositekey;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Класс определяет представление данных, чтение/запись из входного/выходного потока.
 * Каждая строка хранится в виде {Номер полки:Наименование:Автор:Год издания:Количество страниц}
 * Сортировка осуществляется по году издания и по автору
 *
 * @author Irinа Ilina
 */
public class BookReviewVO implements Writable {

    /** Номер полки. */
    private int shelfNumber;

    /** Наименование книги. */
    private String name;

    /** Автор книги. */
    private String author;

    /** Год издания. */
    private int year;

    /** Количество страниц. */
    private int pages;

    /** Конструктор. */
    public BookReviewVO() {
    }

    /**
     * Конструктор.
     *
     * @param shelfNumber номер полки
     * @param name наименование книги
     * @param author автор
     * @param year год издания
     * @param pages количество страниц
     */
    public BookReviewVO(int shelfNumber, String name, String author, int year, int pages) {
        this.shelfNumber = shelfNumber;
        this.name = name;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    /**
     * Возвращает значение поля номер полки.
     *
     * @return номер полки
     */
    public int getShelfNumber() {
        return shelfNumber;
    }

    /**
     * Устанавливает значение поля номер полки.
     *
     * @param shelfNumber номер полки
     */
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    /**
     * Возвращает значение поля наименование книги.
     *
     * @return наименование книги
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает значение поля наименование книги.
     *
     * @param name наименование книги
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает значение поля автор.
     *
     * @return автор
     */
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Возвращает значение поля год издания.
     *
     * @return год издания
     */
    public int getYear() {
        return year;
    }

    /**
     * Устанавливает значение поля год издания.
     *
     * @param year год издания
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Возвращает значение количество страниц.
     *
     * @return количество страниц
     */
    public int getPages() {
        return pages;
    }

    /**
     * Устанавливает значение поля количество страниц.
     *
     * @param pages количество страниц
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Запись данных в выходной поток.
     *
     * @param dataOutput выходной поток
     * @throws IOException исключение, возникающе при доступе к данным с помощью потоков, файлов и каталогов
     */
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(shelfNumber);
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(author);
        dataOutput.writeInt(year);
        dataOutput.writeInt(pages);
    }

    /**
     * Чтение данных из входного потока.
     *
     * @param dataInput входной поток
     * @throws IOException исключение, возникающе при доступе к данным с помощью потоков, файлов и каталогов
     */
    public void readFields(DataInput dataInput) throws IOException {
        shelfNumber = dataInput.readInt();
        name = dataInput.readUTF();
        author = dataInput.readUTF();
        year = dataInput.readInt();
        pages = dataInput.readInt();
    }

    /**
     * Определяет выходного представление данных.
     *
     * @return строка данных
     */
    @Override
    public String toString() {
        return " Карточка книги [" +
                "книжная полка = " + shelfNumber +
                ", наименование = '" + name + '\'' +
                ", автор = '" + author + '\'' +
                ", год издания = " + year +
                ", количество страниц = " + pages +
                ']';
    }
}
