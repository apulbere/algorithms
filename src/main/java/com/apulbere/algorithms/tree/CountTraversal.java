package com.apulbere.algorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class CountTraversal<T> {
    private int i;
    private List<Consumer<Node<T>>> operations = new ArrayList<>(3);

    private void traverseRecursive(Node<T> node) {
        if(node == null) {
            return;
        }
        operations.forEach(op -> op.accept(node));
    }

    CountTraversal<T> left() {
        operations.add(node -> traverseRecursive(node.left));
        return this;
    }

    CountTraversal<T> right() {
        operations.add(node -> traverseRecursive(node.right));
        return this;
    }

    CountTraversal<T> root() {
        operations.add(node -> i++);
        return this;
    }

    int traverse(Node<T> node) {
        traverseRecursive(node);
        return i;
    }
}
