package com.apulbere.collections.sort;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.emptyList;

public class MergeSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * best    O(n log(n))
     * avg     O(n log(n))
     * worst   O(n log(n))
     */
    @Override
    public List<T> sort(List<T> list) {
        return mergeSort(list);
    }

    private List<T> mergeSort(List<T> list) {
        switch(list.size()) {
            case 1: return list;
            case 0: return emptyList();
            default:
                int middle = list.size() / 2;
                var left = mergeSort(list.subList(0, middle));
                var right = mergeSort(list.subList(middle, list.size()));
                return merge(left, right);
        }
    }

    private List<T> merge(List<T> left, List<T> right) {
        var result = new ArrayList<T>(left.size() + right.size());
        var leftIterator = new PeekingIterator<T>(left.iterator());
        var rightIterator = new PeekingIterator<T>(right.iterator());

        while(leftIterator.hasNext() && rightIterator.hasNext()) {
            if(leftIterator.peek().compareTo(rightIterator.peek()) <= 0) {
                result.add(leftIterator.next());
            } else {
                result.add(rightIterator.next());
            }
        }
        leftIterator.forEachRemaining(result::add);
        rightIterator.forEachRemaining(result::add);
        return result;
    }

    @RequiredArgsConstructor
    private class PeekingIterator<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private E peek;

        E peek() {
            return peek == null ? peek = iterator.next() : peek;
        }

        @Override
        public E next() {
            if (peek != null) {
                E res = peek;
                peek = null;
                return res;
            } else {
                return iterator.next();
            }
        }

        @Override
        public boolean hasNext() {
            if (peek != null) {
                return true;
            } else {
                return iterator.hasNext();
            }
        }
    }
}
