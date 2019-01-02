package com.apulbere.collections;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public void add(T value) {
        root = addRecursiveIf(value, root);
    }

    private Node<T> addRecursiveIf(T value, Node<T> node) {
        if(node == null) {
            return new Node<>(value);
        }
        if(value.compareTo(node.value) > 0) {
            node.left = addRecursiveIf(value, node.left);
        } else {
            node.right = addRecursiveIf(value, node.right);
        }
        return node;
    }

    @ToString
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Node<T> {
        Node<T> left;
        Node<T> right;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }
}
