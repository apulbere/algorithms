package com.apulbere.collections.tree;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {

    @Test
    void add() {
        var values = IntStream.rangeClosed(1, 4).boxed().collect(toList());
        var bt = new BinaryTree<>(values);

        var n2 = new Node<>(new Node<>(4), null, 2);
        var n1 = new Node<>(n2, new Node<>(3), 1);
        var expectedBt = new BinaryTree<>(n1);

        assertEquals(expectedBt, bt);
    }
}
