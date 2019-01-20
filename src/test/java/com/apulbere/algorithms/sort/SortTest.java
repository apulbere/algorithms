package com.apulbere.algorithms.sort;

import com.apulbere.algorithms.RandomNumbers;
import com.apulbere.algorithms.SimpleLinkedList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortTest {

    private RandomNumbers random = new RandomNumbers();

    @ParameterizedTest
    @MethodSource("sortImplementation")
    void sort(Sort<Integer> sort) {
        var oddSizeNumbers = List.of(9, 4, 6, 1, 3, 2, 0);
        var expectedSortedOddSizeNumbers = oddSizeNumbers.stream().sorted().collect(toUnmodifiableList());
        assertEquals(expectedSortedOddSizeNumbers, sort.sort(oddSizeNumbers), "list with odd size");

        var evenSizeNumbers = List.of(45, 33, 1, 8, 9, 10, 3, 0);
        var expectedSortedEvenSizeNumbers = evenSizeNumbers.stream().sorted().collect(toUnmodifiableList());
        assertEquals(expectedSortedEvenSizeNumbers, sort.sort(evenSizeNumbers), "list with even size");

        assertEquals(Collections.emptyList(), sort.sort(Collections.emptyList()), "empty list");
    }

    @Test
    @DisplayName("merge sort with O(n) space complexity")
    void sort() {
        sort(new MergeSortOnSpaceComplexity<>());
    }

    @Test
    @DisplayName("merge sort in a linked list does not modify original list")
    void mergeSortLinkedListUnchanged() {
        var numbers = List.of(9, 6, 7);
        var originalLinkedList = new SimpleLinkedList<>(numbers);
        var copyOfOriginalLinkedList = new SimpleLinkedList<>(originalLinkedList);
        var expectedLinkedList = new SimpleLinkedList<>(numbers.stream().sorted().collect(toUnmodifiableList()));

        assertEquals(expectedLinkedList, new MergeSortForLinkedList<Integer>().sort(originalLinkedList));

        assertEquals(copyOfOriginalLinkedList, originalLinkedList, "original linked list remains unchanged");
    }

    @RepeatedTest(7)
    @DisplayName("merge sort in a linked list")
    void mergeSortLinkedList() {
        var numbers = random.randomList();
        var originalLinkedList = new SimpleLinkedList<>(numbers);
        var expectedLinkedList = new SimpleLinkedList<>(numbers.stream().sorted().collect(toUnmodifiableList()));

        assertEquals(expectedLinkedList, new MergeSortForLinkedList<Integer>().sort(originalLinkedList));
    }

    @Disabled
    @RepeatedTest(101)
    void justToBeSure() {
        var numbers = random.randomList();

        assertEquals(numbers.stream().sorted().collect(toList()), new SelectionSort<Integer>().sort(numbers));
    }

    @Test
    @DisplayName("quick sort an ordered list using middle index as pivot")
    void quickSortMedianPivotForSortedList() {
        var chars = List.of('A', 'C', 'E', 'T');
        assertEquals(chars, new QuickSortLomutoPartitionMiddlePivot<Character>().sort(chars));
    }

    private static Iterator<Sort> sortImplementation() {
        return ServiceLoader.load(Sort.class).iterator();
    }
}
