package com.apulbere.collections.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BinaryTree<T> {
    private Node<T> root;

    public BinaryTree(Collection<T> collection) {
        collection.forEach(this::add);
    }

    private void add(T value) {
        if(root == null) {
            root = new Node<>(value);
        } else {
            var queue = new LinkedList<Node<T>>();
            queue.add(root);
            while(!queue.isEmpty()) {
                var node = queue.pop();
                if(node.left == null) {
                    node.left = new Node<>(value);
                    break;
                } else {
                    queue.add(node.left);
                }
                if(node.right == null) {
                    node.right = new Node<>(value);
                    break;
                } else {
                    queue.add(node.right);
                }
            }
        }
    }

    /**
     * or level order traversal
     */
    public List<T> breadthFirstTraversal() {
        var result = new ArrayList<T>();
        var queue = new LinkedList<Node<T>>();
        queue.add(root);
        while(!queue.isEmpty()) {
            var node = queue.pop();
            result.add(node.value);
            acceptNotNull(node.left, queue::add);
            acceptNotNull(node.right, queue::add);
        }
        return result;
    }

    public List<T> breadthFirstTraversal2() {
        return IntStream.rangeClosed(1, height())
                .mapToObj(this::level)
                .flatMap(List::stream)
                .collect(toList());
    }

    public List<T> level(int level) {
        return visitLevel(root, level);
    }

    private List<T> visitLevel(Node<T> node, int level) {
        var res = new ArrayList<T>();
        if(node == null) {
            return res;
        }
        if(level == 1) {
            res.add(node.value);
        } else {
            res.addAll(visitLevel(node.left, level - 1));
            res.addAll(visitLevel(node.right, level - 1));
        }
        return res;
    }

    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node<T> node) {
        if(node == null) {
            return 0;
        }
        return Math.max(heightRecursive(node.left), heightRecursive(node.right)) + 1;
    }

    private <R> void acceptNotNull(R value, Consumer<R> consumer) {
        if(value != null) {
            consumer.accept(value);
        }
    }
}
