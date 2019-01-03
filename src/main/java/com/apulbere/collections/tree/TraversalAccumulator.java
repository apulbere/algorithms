package com.apulbere.collections.tree;

@FunctionalInterface
public interface TraversalAccumulator<T> {
    void add(Node<T> node);
}
