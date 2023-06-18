package com.hadoop.sorting.compositekey;

import com.hadoop.sorting.utils.SortingUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Класс отвечает за модуль сопоставления. Принимает входные данные, анализирует их и передает кортежи.
 *
 * @author Irinа Ilina
 */
public class BookMapper extends Mapper<LongWritable, Text, CustomBookKey, BookReviewVO> {

    @Override
    protected void map(LongWritable key, Text value,
                       Mapper<LongWritable, Text, CustomBookKey, BookReviewVO>.Context context)
            throws IOException, InterruptedException {
        String[] columns = value.toString().split(SortingUtils.COLON_SEPARATOR);
        if (columns.length > 4) {
            // Номер полки: Наименование : Автор : Год издания : Количество страниц
            BookReviewVO bookReviewVO = new BookReviewVO();
            bookReviewVO.setShelfNumber(Integer.parseInt(columns[0]));
            bookReviewVO.setName(columns[1]);
            bookReviewVO.setAuthor(columns[2]);
            bookReviewVO.setYear(Integer.parseInt(columns[3]));
            bookReviewVO.setPages(Integer.parseInt(columns[4]));

            // определяет ключ
            CustomBookKey customKey = new CustomBookKey();
            customKey.setYear(bookReviewVO.getYear());
            customKey.setAuthor(bookReviewVO.getAuthor());

            context.write(customKey, bookReviewVO);
        }
    }

}
