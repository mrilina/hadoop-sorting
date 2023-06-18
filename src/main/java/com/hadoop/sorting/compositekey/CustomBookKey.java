package com.hadoop.sorting.compositekey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

/**
 * Определение ключей и порядка сортировки.
 *
 * @author Irinа Ilina
 */
public class CustomBookKey implements WritableComparable<CustomBookKey> {

    /** Год издания. */
    private Integer year;

    /** Автор. */
    private String author;

    /** Конструктор. */
    public CustomBookKey() {
    }

    /**
     * Конструктор.
     *
     * @param year год издания
     * @param author автор
     */
    public CustomBookKey(Integer year, String author) {
        this.year = year;
        this.author = author;
    }

    /**
     * Возвращает значение поля год издания.
     *
     * @return год издания
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Устанавливает значение поля год издания.
     *
     * @param year год издания
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Возвращает значение поля автор.
     *
     * @return автор
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Устанавливает значение поля автор.
     *
     * @param author автор
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Записывает данные в выходной поток.
     *
     * @param out выходной поток
     * @throws IOException исключение, возникающе при доступе к данным с помощью потоков, файлов и каталогов
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(year);
        out.writeUTF(author);

    }

    /**
     * Читает данные из входного потока.
     *
     * @param in входной поток
     * @throws IOException исключение, возникающе при доступе к данным с помощью потоков, файлов и каталогов
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        year = in.readInt();
        author = in.readUTF();
    }

    /**
     * Определяет порядок сортировки, вначале по году издания, затем по автору произведения.
     * @param key составной ключ
     * @return {число} < 0 (первый объект меньше); > 0 (первый объект больший); 0 (объекты равны)
     */
    @Override
    public int compareTo(CustomBookKey key) {
        int comparedValue = year.compareTo(key.year);
        if (comparedValue != 0) {
            return comparedValue;
        }
        return author.compareTo(key.getAuthor());
    }

}