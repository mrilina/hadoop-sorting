package com.hadoop.sorting.compositekey;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Конфигурация приложения, определение входных параметров.
 *
 * @author Irinа Ilina
 */
public class BookReviewDriver extends Configured implements Tool {
    public static void main(String[] args) {
        try {
            ToolRunner.run(new BookReviewDriver(), args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Настройка параметров и запуск задачи.
     *
     * @param args аргументы командной строки
     * @return код обработки
     * @throws Exception исключительная ситуация
     */

    @Override
    public int run(String[] args) throws Exception {
      // args = new String[] {"books.data", "output"};

        if (args.length != 2) {
            System.err.printf("Usage: %s [generic options] <input1> <output>\n", getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        Job job = Job.getInstance();
        job.setJarByClass(BookReviewDriver.class);
        job.setJobName("BookSortDriver");
        // определение входных данных
        FileInputFormat.addInputPath(job, new Path(args[0]));

        // определение выходных данных
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(BookMapper.class);
        job.setReducerClass(BookReducer.class);
        job.setPartitionerClass(CustomBookPartitioner.class);
        job.setSortComparatorClass(CustomBookKeyComparator.class);
        job.setGroupingComparatorClass(BookGroupComparator.class);
        job.setMapOutputKeyClass(CustomBookKey.class);
        job.setMapOutputValueClass(BookReviewVO.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(BookReviewVO.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

}