package com.apulbere.algorithms.structure;

import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

public class LinkedListBasedQueue<T> {
    private Node<T> head;
    private Node<T> tail;


    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value, null);
        if(tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public T dequeue() {
        if(head == null) {
            throw new NoSuchElementException();
        }
        Node<T> temp = this.head;
        head = this.head.next;

        if (head == null) {
            tail = null;
        }
        return temp.data;
    }

    @AllArgsConstructor
    private static class Node<T> {
        T data;
        Node<T> next;
    }
    
}
