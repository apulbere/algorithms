package com.apulbere.algorithms.sort;

import com.apulbere.algorithms.RandomNumbers;
import com.apulbere.algorithms.SimpleLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortTest {

    private RandomNumbers random = new RandomNumbers();

    @ParameterizedTest
    @MethodSource("sortImplementation")
    void sort(Sort<Integer> sort) {
        var numbers = random.randomList();

        var expectedSortedNumbers = numbers.stream().sorted().collect(toUnmodifiableList());
        assertEquals(expectedSortedNumbers, sort.sort(numbers), "original numbers: " + numbers);
    }

    @RepeatedTest(7)
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

    @Test
    @DisplayName("quick sort an ordered list using middle index as pivot")
    void quickSortMedianPivotForSortedList() {
        var chars = List.of('A', 'C', 'E', 'T');
        assertEquals(chars, new QuickSortMiddlePivot<Character>().sort(chars));
    }

    private static Iterator<Sort> sortImplementation() {
        return ServiceLoader.load(Sort.class).iterator();
    }
}
