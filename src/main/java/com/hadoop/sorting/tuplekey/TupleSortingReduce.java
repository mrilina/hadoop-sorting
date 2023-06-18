package com.hadoop.sorting.tuplekey;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.htuple.Tuple;

import java.io.IOException;

/**
 * Класс редуктор. Принимает кортежи, сформированные в модуле сопоставления, выполняет операцию сводки, которая создает
 * результат меньшего размера, объединяющий данные модуля сопоставления.
 *
 * @author Irina Ilina
 */
public class TupleSortingReduce extends Reducer<Tuple, Text, Text, NullWritable> {
    @Override
    public void reduce(Tuple key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        for (Text value : values) {
            context.write(value, NullWritable.get());
        }
    }
}
