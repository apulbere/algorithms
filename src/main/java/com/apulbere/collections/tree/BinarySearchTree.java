package com.apulbere.collections.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
        return res < 0 ? findRecursive(value, node.left) : findRecursive(value, node.right);
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
}
