package com.apulbere.algorithms.structure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LinkedListBasedQueueTest {

    @Test
    void dequeue() {
        var queue = new LinkedListBasedQueue<String>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
    }
}
