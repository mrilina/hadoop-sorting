package com.hadoop.sorting.compositekey;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

/**
 * Тест на сортировку модуля сопоставления.
 *
 * @author Irina Ilina
 */
public class BookMapperTest {
    String[] books = new String[]{"31852:Война и мир:Толстой Л.Н.:1996:1300",
            "31922:Преступление и наказание:Достоевский Ф.М:2022:672",
            "32122:Приключения Буратино, или Золотой Ключик:Толстой А.Н.:2020:128",
            "32122:Зачем нужна геология. Краткая история прошлого и будущего нашей планеты:Макдугалл Дуг:2022:400",
            "12390:Коппола. Семья, изменившая кинематограф:Натан Айан:2023:448",
            "31852:Беседы о русской культуре. Быт и традиции русского дворянства (XVIII - начало XIX века):Лотман Ю.М.:2023:560",
            "31922:Король и Шут. Старая книга:Князев А.С.:2018:280"};

    @Test
    public void testCombiningMapper() throws Exception {
    /*    new MapDriver<LongWritable, Text, CustomBookKey, BookReviewVO>()
                .withMapper(new BookMapper())
                .withInput(new LongWritable(1), new Text(books[0]))
                .withOutput(new CustomBookKey(1996, "Толстой Л.Н."),
                            new BookReviewVO(31852, "Война и мир", "Толстой Л.Н.", 1996, 1300))
                .runTest();*/
    }
}
