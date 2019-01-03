package com.apulbere.collections;

import com.apulbere.collections.BinarySearchTree.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        var preorder = List.of(5, 4, 3, 15, 10, 27);
        assertEquals(preorder, bst.preorder());

        var postOrder = List.of(3, 4, 10, 27, 15, 5);
        assertEquals(postOrder, bst.postorder());
    }
}