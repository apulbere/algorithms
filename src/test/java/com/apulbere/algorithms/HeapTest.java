package com.apulbere.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HeapTest {

    @Test
    void add() {
        var expectedHeap = new Heap<>(List.of(-2, 3, 1, 5, 9, 8, 6));

        var heap1 = new Heap<>(List.of(1, 3, 6, 5, 9, 8));
        heap1.add(-2);

        assertEquals(expectedHeap, heap1);
    }

    @RepeatedTest(11)
    void min() {
        var randomNumbers = random().limit(randomBetween(1, 13)).collect(toList());
        var heap = new Heap<Integer>();
        randomNumbers.forEach(heap::add);

        assertEquals(Collections.min(randomNumbers), heap.min(), "for numbers: " + randomNumbers + " and " + heap);
    }

    @RepeatedTest(23)
    void removeMin() {
        var randomNumbers = random().limit(randomBetween(2, 21)).collect(toList());

        var heap = new Heap<Integer>();
        randomNumbers.forEach(heap::add);
        var copyHeap = new Heap<Integer>();
        randomNumbers.forEach(copyHeap::add);

        heap.removeMin();
        randomNumbers.remove(Collections.min(randomNumbers));

        assertEquals(Collections.min(randomNumbers), heap.min(),
                "for numbers: " + randomNumbers + "\noriginal heap: " + copyHeap + "\naltered heap: " + heap);
    }

    @Test
    @DisplayName("remove min till there are no more elements in heap")
    void removeMin2() {
        var heap = new Heap<>(List.of(17, 23, 54));

        heap.removeMin();
        assertEquals(23, (int) heap.min());

        heap.removeMin();
        assertEquals(54, (int) heap.min());

        heap.removeMin();
        assertNull(heap.min());
    }

    private Stream<Integer> random() {
        return IntStream.generate(() -> randomBetween(0, 100)).boxed();
    }

    private int randomBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
