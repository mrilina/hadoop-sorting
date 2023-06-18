package com.hadoop.sorting.compositekey;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на сортировку композитных ключей.
 *
 * @author Irina Ilina
 */
public class CustomBookKeyTest {

    /** Тест на сортировку по полю год. */
    @Test
    public void testCompareTo() {
        CustomBookKey customBookKey1 = new CustomBookKey(2023, "Беседы о русской культуре. Быт и традиции русского дворянства (XVIII - начало XIX века):Лотман Ю.М.");
        CustomBookKey customBookKey2 = new CustomBookKey(1996,"Война и мир:Толстой Л.Н.");

        List<CustomBookKey> customBookKeysList = new ArrayList<>();
        customBookKeysList.add(customBookKey1);
        customBookKeysList.add(customBookKey2);
        Collections.sort(customBookKeysList);

        assertThat(customBookKeysList.get(0), is(customBookKey2));
        assertThat(customBookKeysList.get(1), is(customBookKey1));
    }

    /** Тест на сортировку по совпадающему полю год и полю наименование. */
    @Test
    public void testCompareToSameYear() {
        CustomBookKey customBookKey1 = new CustomBookKey(2023,"Натан Айан");
        CustomBookKey customBookKey2 = new CustomBookKey(2023, "Лотман Ю.М.");

        List<CustomBookKey> customBookKeysList = new ArrayList<>();
        customBookKeysList.add(customBookKey1);
        customBookKeysList.add(customBookKey2);
        Collections.sort(customBookKeysList);

        assertThat(customBookKeysList.get(0), is(customBookKey2));
        assertThat(customBookKeysList.get(1), is(customBookKey1));
    }
}
