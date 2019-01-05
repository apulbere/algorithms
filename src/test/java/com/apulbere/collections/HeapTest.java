package com.apulbere.collections;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        var randomNumbers = IntStream.generate(() -> (int)(Math.random() * 100)).limit(31).boxed().collect(toList());
        var heap = new Heap<Integer>();
        randomNumbers.forEach(heap::add);

        assertEquals(Collections.min(randomNumbers), heap.min());
    }
}
