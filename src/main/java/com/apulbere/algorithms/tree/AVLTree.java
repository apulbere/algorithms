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
        return balance(node);
    }

    private AVLNode<T> balance(AVLNode<T> x) {
        int balance = findBalanceFactor(x);
        if (balance < -1) {
            if (findBalanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if (balance > 1) {
            if (findBalanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private AVLNode<T> rotateRight(AVLNode<T> y) {
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
        return node != null ? node.height : -1;
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
