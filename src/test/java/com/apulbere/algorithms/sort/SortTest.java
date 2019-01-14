package com.apulbere.algorithms.sort;

import com.apulbere.algorithms.SimpleLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortTest {

    @ParameterizedTest
    @MethodSource("sortImplementation")
    void sort(Sort<Integer> sort) {
        var numbers = random().limit(randomBetween(1, 67)).collect(toUnmodifiableList());

        var expectedSortedNumbers = numbers.stream().sorted().collect(toUnmodifiableList());
        assertEquals(expectedSortedNumbers, sort.sort(numbers), "original numbers: " + numbers);
    }

    @RepeatedTest(23)
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
        var numbers = random().limit(randomBetween(1, 67)).collect(toUnmodifiableList());
        var originalLinkedList = new SimpleLinkedList<>(numbers);
        var expectedLinkedList = new SimpleLinkedList<>(numbers.stream().sorted().collect(toUnmodifiableList()));

        assertEquals(expectedLinkedList, new MergeSortForLinkedList<Integer>().sort(originalLinkedList));
    }

    private static Iterator<Sort> sortImplementation() {
        return ServiceLoader.load(Sort.class).iterator();
    }

    private Stream<Integer> random() {
        return IntStream.generate(() -> randomBetween(0, 100)).boxed();
    }

    private int randomBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
