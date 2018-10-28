package com.apulbere.collections;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

@NoArgsConstructor
public class SimpleLinkedList<T> implements Iterable<T> {
    private Node<T> head;

    public SimpleLinkedList(Collection<T> values) {
        values.forEach(this::append);
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
        Node<T> temp = head;
        head = new Node<>(value, temp);
    }

    /**
     * O(n)
     *
     * O(1) only when you have a reference to the node you want to remove
     */
    public void delete(T value) {
        if(head != null) {
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
        return StreamSupport.stream(this.spliterator(), false).anyMatch(item -> item.equals(value));
    }

    public long size() {
        return StreamSupport.stream(this.spliterator(), false).count();
    }

    /**
     *         Node<T> temp = head;
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

    @AllArgsConstructor
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
}
