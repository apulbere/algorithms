package com.apulbere.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {

    /**
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
            sort(list, pi + 1, high);
        }
    }

    private int partition(List<T> list, int low, int high) {
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

    private void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
