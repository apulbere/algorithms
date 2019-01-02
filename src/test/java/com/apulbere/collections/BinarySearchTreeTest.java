package com.apulbere.collections;

import com.apulbere.collections.BinarySearchTree.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeTest {

    @Test
    void correctInsertion() {
        var bst = new BinarySearchTree<Integer>();
        bst.add(4);
        bst.add(6);
        bst.add(23);
        bst.add(5);
        bst.add(1);

        var node6 = new Node<>(new Node<>(23), new Node<>(5), 6);
        var node1 = new Node<>(1);
        var rootNode = new Node<>(node6, node1, 4);
        var expectedBst = new BinarySearchTree<>(rootNode);
        assertEquals(expectedBst, bst);
    }
}
