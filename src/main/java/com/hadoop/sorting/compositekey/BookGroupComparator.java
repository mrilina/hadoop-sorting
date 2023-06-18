package com.hadoop.sorting.compositekey;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Класс определяет порядок сортировки групп.
 *
 * @author Irinа Ilina
 */
public class BookGroupComparator extends WritableComparator {

    protected BookGroupComparator() {
        super(CustomBookKey.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        CustomBookKey customBookKey1 = (CustomBookKey) w1;
        CustomBookKey customBookKey2 = (CustomBookKey) w2;
        return customBookKey1.getYear().compareTo(customBookKey2.getYear());
    }
}