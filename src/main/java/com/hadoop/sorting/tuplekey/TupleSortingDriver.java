package com.hadoop.sorting.tuplekey;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.htuple.ShuffleUtils;
import org.htuple.Tuple;

/**
 * Конфигурация приложения, определение входных параметров.
 *
 * @author Irina Ilina
 */
public class TupleSortingDriver extends Configured implements Tool {

    /**
     * Основная программа, задание конфигурации.
     *
     * @param args список аргументов
     * @throws Exception исключительная ситуация
     */
    public static void main(final String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new TupleSortingDriver(), args);
        System.exit(res);
    }

    /**
     * Настройка параметров и запуск задачи.
     *
     * @param args аргументы командной строки
     * @return код обработки
     * @throws Exception исключительная ситуация
     */
    public int run(final String[] args) throws Exception {
        // args = new String[] {"names.data", "output"};
        String input = args[0];
        String output = args[1];

        Configuration conf = super.getConf();

        setupSecondarySort(conf);

        Job job = new Job(conf);
        job.setJarByClass(TupleSortingDriver.class);
        job.setJobName("TupleSortingDriver");

        job.setMapperClass(TupleSortingMap.class);
        job.setReducerClass(TupleSortingReduce.class);
        job.setMapOutputKeyClass(Tuple.class);
        job.setMapOutputValueClass(Text.class);

        Path outputPath = new Path(output);

        FileInputFormat.setInputPaths(job, input);
        FileOutputFormat.setOutputPath(job, outputPath);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    /**
     * Партиционирование и группировка по первому колю - ключу.
     * Сортировка производится по обоим полям, ключу и значению, разделенными ":".
     *
     * @param conf Hadoop конфигурация
     */
    public static void setupSecondarySort(Configuration conf) {
        ShuffleUtils.configBuilder()
                .useNewApi()
                .setPartitionerIndices(TupleSortingFields.FIRST_KEY)
                .setSortIndices(TupleSortingFields.values())
                .setGroupIndices(TupleSortingFields.SECOND_VALUE)
                .configure(conf);
    }

}
