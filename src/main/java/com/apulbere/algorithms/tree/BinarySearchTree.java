package com.apulbere.algorithms.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

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
        return findRecursive(value, res < 0 ? node.left : node.right);
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

    public List<T> inorder2() {
        return new AbstractTraversal<ListAccumulator<T>, T>(new ListAccumulator<>(new LinkedList<>()))
                .left()
                .root()
                .right()
                .traverse(root)
                .list;
    }

    @AllArgsConstructor
    private static class ListAccumulator<T> implements TraversalAccumulator<T> {
        List<T> list;

        @Override
        public void add(Node<T> node) {
            list.add(node.value);
        }
    }

    public int countIterative() {
        var queue = new LinkedList<Node<T>>();
        queue.push(root);
        int i = 0;
        while(!queue.isEmpty()) {
            var node = queue.pop();
            if(node.left != null) {
                queue.push(node.left);
            }
            if(node.right != null) {
                queue.push(node.right);
            }
            i++;
        }
        return i;
    }

    public int countRecursive() {
        return new CountTraversal<T>().left().right().root().traverse(root);
    }

    public int countLeafs() {
        return new AbstractTraversal<LeafCounter<T>, T>(new LeafCounter<>())
                .left()
                .right()
                .root()
                .traverse(root)
                .count;
    }

    public int countFullNodes() {
        return new AbstractTraversal<FullNodeCounter<T>, T>(new FullNodeCounter<>())
                .left()
                .right()
                .root()
                .traverse(root)
                .count;
    }

    private static class LeafCounter<T> implements TraversalAccumulator<T> {
        int count;

        @Override
        public void add(Node<T> node) {
            if(node.left == null && node.right == null) {
                count++;
            }
        }
    }

    private static class FullNodeCounter<T> implements TraversalAccumulator<T> {
        int count;

        @Override
        public void add(Node<T> node) {
            if(node.left != null && node.right != null) {
                count++;
            }
        }
    }

    /**
     * tree with only a node (the root) has a height of zero -- Wiki
     */
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node node) {
        if(node == null) {
            return 0;
        }
        return Math.max(heightRecursive(node.right), heightRecursive(node.left)) + 1;
    }

    public T min() {
        return root == null ? null : traverseTillNull(root, node -> node.left);
    }

    public T min2() {
        return new AbstractTraversal<>(new ValueAccumulator<T>()).root().left().traverse(root).value;
    }

    public T max() {
        return root == null ? null : traverseTillNull(root, node -> node.right);
    }

    public T max2() {
        return new AbstractTraversal<>(new ValueAccumulator<T>()).root().right().traverse(root).value;
    }

    private static class ValueAccumulator<T> implements TraversalAccumulator<T> {
        T value;

        @Override
        public void add(Node<T> node) {
            value = node.value;
        }
    }

    private T traverseTillNull(Node<T> node, Function<Node<T>, Node<T>> nodeFunction) {
        var next = nodeFunction.apply(node);
        return next == null ? node.value : traverseTillNull(next, nodeFunction);
    }

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    private Node<T> deleteRecursive(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        int res = value.compareTo(node.value);
        if(res == 0) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            T smallestValue = traverseLeft(node.left);
            node.value = smallestValue;
            node.left = deleteRecursive(node.left, smallestValue);
            return node;

        }
        if (res < 0) {
            node.left = deleteRecursive(node.left, value);
            return node;
        }
        node.right = deleteRecursive(node.right, value);
        return node;
    }

    private T traverseLeft(Node<T> node) {
        if(node.left == null && node.right == null) {
            return node.value;
        }
        return traverseLeft(node.left != null ? node.left : node.right);
    }
}
