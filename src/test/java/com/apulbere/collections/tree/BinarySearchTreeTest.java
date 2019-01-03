package com.apulbere.collections.tree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    @Test
    void correctInsertion() {
        var bst = new BinarySearchTree<Integer>();
        bst.add(4);
        bst.add(6);
        bst.add(23);
        bst.add(5);
        bst.add(1);

        var node6 = new Node<>(new Node<>(5), new Node<>(23), 6);
        var node1 = new Node<>(1);
        var rootNode = new Node<>(node1, node6, 4);
        var expectedBst = new BinarySearchTree<>(rootNode);
        assertEquals(expectedBst, bst);
    }

    @Test
    void contains() {
        var values = List.of(5, 8, 9, 1, 0);
        var bst = new BinarySearchTree<>(values);

        assertTrue(values.stream().allMatch(bst::contains));
        assertFalse(bst.contains(89));
    }

    @Test
    void traverse() {
        var bst = new BinarySearchTree<>(List.of(5, 15, 4, 10, 27, 3));

        var inorder = List.of(3, 4, 5, 10, 15, 27);
        assertEquals(inorder, bst.inorder());
        assertEquals(inorder, bst.inorder2());

        var preorder = List.of(5, 4, 3, 15, 10, 27);
        assertEquals(preorder, bst.preorder());

        var postOrder = List.of(3, 4, 10, 27, 15, 5);
        assertEquals(postOrder, bst.postorder());
    }

    @Test
    void count() {
        var values = List.of('A', 'C', 'N', 'L', 'I', 'A', 'T');
        var bst = new BinarySearchTree<>(values);

        assertEquals(values.size(), bst.countIterative());
        assertEquals(values.size(), bst.countRecursive());

        var numbers = List.of(56, 34, 67, 12, 40, 41);
        var numbBst = new BinarySearchTree<>(numbers);

        assertEquals(2, numbBst.countFullNodes());
        assertEquals(3, numbBst.countLeafs());
    }

    @Test
    void height() {
        var bst = new BinarySearchTree<>(List.of(78, 34, 23, 89, 77, 76));
        assertEquals(4, bst.height());

        var numbers = IntStream.rangeClosed(1, 15).boxed().collect(toList());
        assertEquals(numbers.size(), new BinarySearchTree<>(numbers).height());
    }

    @Test
    void findMinAndMax() {
        assertNull(new BinarySearchTree<>().max());
        assertNull(new BinarySearchTree<>().max2());

        var values = List.of(78, 34, 23, 89, 77, 76);
        var bst = new BinarySearchTree<>(values);
        assertEquals(Collections.max(values), bst.max());
        assertEquals(Collections.max(values), bst.max2());
        assertEquals(Collections.min(values), bst.min());
        assertEquals(Collections.min(values), bst.min2());

        var seq = IntStream.rangeClosed(0, 20).boxed().collect(toList());
        var seqBst = new BinarySearchTree<>(seq);
        assertEquals(20, (int) seqBst.max());
        assertEquals(20, (int) seqBst.max2());
        assertEquals(0, (int) seqBst.min());
        assertEquals(0, (int) seqBst.min2());
    }
}
