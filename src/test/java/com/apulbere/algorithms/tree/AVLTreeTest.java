package com.apulbere.algorithms.tree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AVLTreeTest {

    @Test
    @DisplayName("insert Left Left case")
    void insertLL() {
        var avlTree = new AVLTree<Integer>();
        var numbers = List.of(10, 5, 15, -10, -20);

        numbers.forEach(avlTree::insert);

        assertEquals(numbers.stream().sorted().collect(toList()), avlTree.inorder());
        assertEquals(2, avlTree.getRoot().height);
    }

    @Test
    @DisplayName("insert Left Right case")
    void insertLR() {
        var avlTree = new AVLTree<Integer>();
        var numbers = List.of(10, 5, 15, -10, -5);

        numbers.forEach(avlTree::insert);

        assertEquals(numbers.stream().sorted().collect(toList()), avlTree.inorder());
        assertEquals(2, avlTree.getRoot().height);
    }

    @Test
    @DisplayName("insert Right Right case")
    void insertRR() {
        var avlTree = new AVLTree<Integer>();
        var numbers = List.of(10, 5, 20, 30, 40);

        numbers.forEach(avlTree::insert);

        assertEquals(numbers.stream().sorted().collect(toList()), avlTree.inorder());
        assertEquals(2, avlTree.getRoot().height);
    }

    @Test
    @DisplayName("insert Right Left case")
    void insertRL() {
        var avlTree = new AVLTree<Integer>();
        var numbers = List.of(10, 5, 20, 40, 30);

        numbers.forEach(avlTree::insert);

        assertEquals(numbers.stream().sorted().collect(toList()), avlTree.inorder());
        assertEquals(2, avlTree.getRoot().height);
    }
}
