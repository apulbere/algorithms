package com.apulbere.collections.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class AbstractTraversal<A extends TraversalAccumulator<T>, T> {
    private A accumulator;
    private List<Consumer<Node<T>>> operations = new ArrayList<>(3);

    private void traverseRecursive(Node<T> node) {
        if(node == null) {
            return;
        }
        operations.forEach(op -> op.accept(node));
    }

    public AbstractTraversal(A accumulator) {
        this.accumulator = accumulator;
    }

    AbstractTraversal<A, T> left() {
        operations.add(node -> traverseRecursive(node.left));
        return this;
    }

    AbstractTraversal<A, T> right() {
        operations.add(node -> traverseRecursive(node.right));
        return this;
    }

    AbstractTraversal<A, T> root() {
        operations.add(node -> accumulator.add(node));
        return this;
    }

    A traverse(Node<T> node) {
        traverseRecursive(node);
        return accumulator;
    }
}
