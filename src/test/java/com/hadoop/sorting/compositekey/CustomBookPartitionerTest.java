package com.hadoop.sorting.compositekey;

import org.apache.hadoop.io.NullWritable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на распределение ключей.
 *
 * @author Irina Ilina
 */
public class CustomBookPartitionerTest {

    @Test
    public void testGetPartition() throws Exception {
        CustomBookKey dataKey = new CustomBookKey(2018, "Король и Шут. Старая книга:Князев А.С.");
        int expectedPartition = dataKey.getYear().hashCode() % 3;

        CustomBookPartitioner partitioner = new CustomBookPartitioner();
        int partition = partitioner.getPartition(dataKey, NullWritable.get(),3);
        assertThat(expectedPartition, is(partition));
    }
}
