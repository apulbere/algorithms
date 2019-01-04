package com.apulbere.collections.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedList;

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
}
