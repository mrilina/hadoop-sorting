package com.hadoop.sorting.tuplekey;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.htuple.Tuple;

import java.io.IOException;

/**
 * Класс отвечает за модуль сопоставления. Принимает входные данные, анализирует их и передает кортежи.
 *
 * @author Irina Ilina
 */
public class TupleSortingMap extends Mapper<LongWritable, Text, Tuple, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String parts[] = value.toString().split(":");

        Tuple outputKey = new Tuple();
        outputKey.set(TupleSortingFields.FIRST_KEY, parts[0]);
        outputKey.set(TupleSortingFields.SECOND_VALUE, parts[1]);

        context.write(outputKey, value);
    }

}
