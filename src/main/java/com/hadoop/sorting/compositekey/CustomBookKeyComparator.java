package com.hadoop.sorting.compositekey;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Класс определяет порядок сортировки ключей.
 *
 * @author Irinа Ilina
 */
public class CustomBookKeyComparator extends WritableComparator {

    /** Конструктор. */
    protected CustomBookKeyComparator() {
        super(CustomBookKey.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        CustomBookKey customBookKey1 = (CustomBookKey) w1;
        CustomBookKey customBookKey2 = (CustomBookKey) w2;
        int cmp = customBookKey1.getYear().compareTo(customBookKey2.getYear());
        if (cmp != 0) {
            return cmp;
        }
        return customBookKey1.getAuthor().compareTo(customBookKey2.getAuthor());
    }
}