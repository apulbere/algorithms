package com.apulbere.algorithms.structure;

import lombok.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SimpleLinkedList<T> implements Iterable<T> {
    @Getter
    private Node<T> head;

    public SimpleLinkedList(Collection<T> values) {
        values.forEach(this::append);
    }

    public SimpleLinkedList(SimpleLinkedList<T> linkedList) {
        head = linkedList.head.copy();
    }

    /**
     * O(N)
     */
    public void append(T value) {
        if(head == null) {
            head = new Node<>(value);
        } else {
            Node<T> last = head;
            while(last.next != null) {
                last = last.next;
            }
            last.next = new Node<>(value);
        }
    }

    /**
     * O(1)
     */
    public void prepend(T value) {
        head = new Node<>(value, head);
    }

    /**
     * O(n)
     *
     * O(1) only when you have a reference to the node you want to remove
     */
    public void delete(T value) {
        if(!isEmpty()) {
            Node<T> current = head;
            Node<T> prev = null;
            while (current != null && !current.data.equals(value)) {
                prev = current;
                current = current.next;
            }
            if(prev == null) {
                head = null;
            } else {
                prev.next = current != null ? current.next : null;
            }
        }
    }

    /**
     * search complexity
     * O(N)
     */
    public boolean contains(T value) {
        return stream().anyMatch(n -> n.data.equals(value));
    }

    public long size() {
        return stream().count();
    }

    private Stream<Node<T>> stream() {
        return Stream.iterate(head, Objects::nonNull, Node::getNext);
    }

    /**
     *         AVLNode<T> temp = head;
     *         while (temp != null) {
     *             //use temp.data
     *             temp = temp.next;
     *         }
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    public Collection<T> toCollection(Supplier<Collection<T>> supplier) {
        Collection<T> collection = supplier.get();
        forEach(collection::add);
        return collection;
    }

    public Node<T> findMiddle() {
        return isEmpty() ? null : head.middle();
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Data
    @AllArgsConstructor
    public static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }

        public Node<T> middle() {
            var fast = this.next;
            var slow = this;
            while(fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        public Node<T> copy() {
            return copy(this);
        }

        private Node<T> copy(Node<T> node) {
            return node == null ? null : new Node<>(node.data, copy(node.next));
        }
    }
}
