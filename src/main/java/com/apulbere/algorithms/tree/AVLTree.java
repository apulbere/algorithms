package com.apulbere.algorithms.tree;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class AVLTree<T extends Comparable<T>> {
    @Getter
    private AVLNode<T> root;

    public void insert(T value) {
        root = insert(root, value);
    }

    private AVLNode<T> insert(AVLNode<T> node, T value) {
        if(node == null) {
            return new AVLNode<>(value);
        }
        if(value.compareTo(node.value) < 0) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        node.height = max(height(node.left), height(node.right)) + 1;
        int balance = findBalanceFactor(node);

        // Left Left case
        if (balance > 1 && value.compareTo(node.left.value) < 0)
            return rotateRight(node);

        // Right Right case
        if (balance < -1 && value.compareTo(node.right.value) > 0)
            return rotateLeft(node);

        // Left Right case
        if (balance > 1 && value.compareTo(node.left.value) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left case
        if (balance < -1 && value.compareTo(node.right.value) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    AVLNode<T> rotateLeft(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.left;
        AVLNode<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    int findBalanceFactor(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    int height(AVLNode node) {
        return node != null ? node.height : 0;
    }

    List<T> inorder() {
        var result = new ArrayList<T>();
        inorder(result, root);
        return result;
    }

    void inorder(List<T> result, AVLNode<T> node) {
        if(node == null) {
            return;
        }
        inorder(result, node.left);
        result.add(node.value);
        inorder(result, node.right);
    }

    @ToString
    public static class AVLNode<T> {
        AVLNode<T> left;
        AVLNode<T> right;
        T value;
        int height;

        AVLNode(T value) {
            this.value = value;
        }


    }
}
