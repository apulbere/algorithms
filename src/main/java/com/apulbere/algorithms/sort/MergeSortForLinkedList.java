package com.apulbere.algorithms.sort;

import com.apulbere.algorithms.SimpleLinkedList;
import com.apulbere.algorithms.SimpleLinkedList.Node;

public class MergeSortForLinkedList<T extends Comparable<T>> {

    /**
     * mergeSort(head)
     *
     * STEP 1: if head is NULL or there is only one element in the linked list then return the head
     *
     * STEP 2: divide the linked list into two equal halves.
     *
     * STEP 3: sort the two halves firstHalf and secondHalf.
     *         mergeSort(firstHalf)
     *         mergeSort(secondHalf)
     *
     * STEP 4: merge the sorted firstHalf and secondHalf (using mergeSort() recursively) and update the head pointer using head.
     *         head = mergeSort(firstHalf, secondHalf)
     *
     * Performance:
     *  best     O(n log n)
     *  avg      O(n log n)
     *  worst    O(n log n)
     * Space complexity: O(log n)
     */
    public SimpleLinkedList<T> sort(SimpleLinkedList<T> list) {
        var headCopy = list.isEmpty() ? null : list.getHead().copy();
        return new SimpleLinkedList<>(mergeSort(headCopy));
    }

    private Node<T> mergeSort(Node<T> head) {
        if(head == null || head.getNext() == null) {
            return head;
        }

        var middle = head.middle();
        var nextToMiddle = middle.getNext();
        middle.setNext(null);
        var left = mergeSort(head);
        var right = mergeSort(nextToMiddle);

        return merge(left, right);
    }

    private Node<T> merge(Node<T> left, Node<T> right) {
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        if(left.getData().compareTo(right.getData()) <= 0) {
            return new Node<>(left.getData(), merge(left.getNext(), right));
        } else {
            return new Node<>(right.getData(), merge(right.getNext(), left));
        }
    }
}
