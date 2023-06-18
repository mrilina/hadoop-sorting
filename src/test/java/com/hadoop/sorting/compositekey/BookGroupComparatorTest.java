package com.hadoop.sorting.compositekey;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на сортировку групп.
 *
 * @author Irina Ilina
 */
public class BookGroupComparatorTest {

    /** Первая книга. */
    private CustomBookKey customBookKey1 = new CustomBookKey(2020, "Толстой А.Н.");

    /** Вторая книга. */
    private CustomBookKey customBookKey2 = new CustomBookKey(2023,"Натан Айан");

    private BookGroupComparator comparator = new BookGroupComparator();

    /** Тест на упорядоченность 1 и 2 книг. */
    @Test
    public void testCompare() {
        assertThat(comparator.compare(customBookKey1, customBookKey2), is(-1));
    }

    /** Тест на упорядоченность 2 и 1 книг. */
    @Test
    public void testCompareGreater() {
        assertThat(comparator.compare(customBookKey2, customBookKey1), is(1));
    }

    /** Тест на равенство книг. */
    @Test
    public void testCompareEquals() {
        assertThat(comparator.compare(customBookKey2, customBookKey2), is(0));
    }
}
