package com.apulbere.algorithms.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

class Traversal<T> {
    private List<T> list = new LinkedList<>();
    private List<Consumer<Node<T>>> operations = new ArrayList<>(3);

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
