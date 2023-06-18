package com.hadoop.sorting.compositekey;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Objects;

/**
 * Класс Partitioner, распределяет промежуточные ключи, собирает промежуточные результаты маппирования и агрегирует их.
 *
 * @author Irinа Ilina
 */
public class CustomBookPartitioner extends Partitioner<CustomBookKey, NullWritable> {

    @Override
    public int getPartition(CustomBookKey key, NullWritable value, int numPartitions) {
        return Math.abs(Objects.hashCode(key.getYear()) * 127) % numPartitions;
    }
}