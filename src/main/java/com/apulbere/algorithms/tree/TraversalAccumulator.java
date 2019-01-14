package com.apulbere.algorithms.tree;

@FunctionalInterface
public interface TraversalAccumulator<T> {
    void add(Node<T> node);
}
