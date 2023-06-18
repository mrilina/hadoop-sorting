package com.hadoop.sorting.compositekey;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Класс редуктор. Принимает кортежи, сформированные в модуле сопоставления, выполняет операцию сводки, которая создает
 * результат меньшего размера, объединяющий данные модуля сопоставления.
 *
 * @author Irinа Ilina
 */
public class BookReducer extends Reducer<CustomBookKey, BookReviewVO, NullWritable, BookReviewVO> {

    @Override
    protected void reduce(CustomBookKey key, Iterable<BookReviewVO> values,
                          Reducer<CustomBookKey, BookReviewVO, NullWritable, BookReviewVO>.Context context)
            throws IOException, InterruptedException {
        for (BookReviewVO bookReviewVO : values) {
            context.write(NullWritable.get(), bookReviewVO);
        }
    }
}
