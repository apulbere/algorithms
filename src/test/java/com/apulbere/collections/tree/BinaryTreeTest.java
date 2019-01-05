package com.apulbere.collections.tree;

import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    void height() {
        var values1 = IntStream.rangeClosed(1, 8).boxed().collect(toList());
        var bt1 = new BinaryTree<>(values1);
        assertEquals(4, bt1.height());

        var values2 = List.of(1, 2);
        var bt2 = new BinaryTree<>(values2);
        assertEquals(2, bt2.height());
    }

    @Test
    void breadthFirstTraversal() {
        var values1 = IntStream.rangeClosed(1, 8).boxed().collect(toList());
        var bt1 = new BinaryTree<>(values1);

        assertEquals(values1, bt1.breadthFirstTraversal());
        assertEquals(values1, bt1.breadthFirstTraversal2());
    }

    @Test
    void getLevel() {
        var values1 = IntStream.rangeClosed(1, 8).boxed().collect(toList());
        var bt1 = new BinaryTree<>(values1);

        assertEquals(List.of(1), bt1.level(1));
        assertEquals(List.of(2, 3), bt1.level(2));
        assertEquals(List.of(4, 5, 6, 7), bt1.level(3));
        assertEquals(List.of(8), bt1.level(4));
    }
}
