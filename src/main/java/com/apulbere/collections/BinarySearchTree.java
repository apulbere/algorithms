package com.apulbere.collections;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.function.Consumer;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinarySearchTree(Collection<T> collection) {
        collection.forEach(this::add);
    }

    public void add(T value) {
        Objects.requireNonNull(value, "null value not allowed");
        root = addRecursive(value, root);
    }

    private Node<T> addRecursive(T value, Node<T> node) {
        if(node == null) {
            return new Node<>(value);
        }
        if(value.compareTo(node.value) < 0) {
            node.left = addRecursive(value, node.left);
        } else {
            node.right = addRecursive(value, node.right);
        }
        return node;
    }

    public boolean contains(T value) {
        Objects.requireNonNull(value, "null value not allowed");
        return findRecursive(value, root) != null;
    }

    private Node<T> findRecursive(T value, Node<T> node) {
        if(node == null) {
            return null;
        }
        int res = value.compareTo(node.value);
        if(res == 0) {
            return node;
        }
        return res < 0 ? findRecursive(value, node.left) : findRecursive(value, node.right);
    }

    public List<T> inorder() {
        return new Traversal<T>().left().root().right().traverse(root);
    }

    public List<T> preorder() {
        return new Traversal<T>().root().left().right().traverse(root);
    }

    public List<T> postorder() {
        return new Traversal<T>().left().right().root().traverse(root);
    }

    private static class Traversal<T> {
        List<T> list = new LinkedList<>();
        List<Consumer<Node<T>>> operations = new ArrayList<>(3);

        private void traverseRecursive(Node<T> node) {
            if(node == null) {
                return;
            }
            operations.forEach(op -> op.accept(node));
        }

        Traversal<T> left() {
            operations.add(node -> traverseRecursive(node.left));
            return this;
        }

        Traversal<T> right() {
            operations.add(node -> traverseRecursive(node.right));
            return this;
        }

        Traversal<T> root() {
            operations.add(node -> list.add(node.value));
            return this;
        }

        List<T> traverse(Node<T> node) {
            traverseRecursive(node);
            return list;
        }
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
