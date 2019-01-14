package com.apulbere.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {

    /**
     *
     * STEP 1: ick an element, called a pivot, from the array.
     * STEP 2: Partitioning: reorder the array so that all elements with values less than the pivot come before the pivot, while all elements with values greater
     *         than the pivot come after it (equal values can go either way). After this partitioning, the pivot is in its final position.
     * STEP 3: Recursively apply the above steps to the sub-array of elements with smaller values and separately to the sub-array of elements with greater values.
     *
     * Performance:
     *  worst:      O(n^2)
     *  avg:        O(n log n)
     *  best:       O(n log n)
     *
     * Space complexity: O(n)
     */
    @Override
    public List<T> sort(List<T> list) {
        var result = new ArrayList<>(list);
        sort(result, 0, result.size() - 1);
        return result;
    }

    private void sort(List<T> list, int low, int high) {
        if(low < high) {
            int pi = partition(list, low, high);
            sort(list, low, pi - 1);
            sort(list, pi, high); // pi + 1
        }
    }

    protected int partition(List<T> list, int low, int high) {
        T pivot = list.get(high);
        int pi = low;
        for(int i = low; i < high; i++) {
            T current = list.get(i);
            if(current.compareTo(pivot) <= 0) {
                swap(list, i, pi++);
            }
        }
        swap(list, pi, high);
        return pi;
    }

    void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
