package com.apulbere.algorithms.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
class Node<T> {
    Node<T> left;
    Node<T> right;
    T value;

    Node(T value) {
        this.value = value;
    }
}
