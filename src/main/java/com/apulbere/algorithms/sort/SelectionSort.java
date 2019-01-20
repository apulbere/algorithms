package com.apulbere.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * repeatedly find the minimum element from unsorted part and putting it at the beginning
     *
     * Performance:
     *  best    O(n^2)
     *  avg     O(n^2)
     *  worst   O(n^2)
     *
     * Space complexity: O(n) //O(1) for mutable list
     */
    @Override
    public List<T> sort(List<T> list) {
        var result = new ArrayList<>(list);
        for(int i = 0; i < list.size(); i++) {
            sort(result, i);
        }
        return result;
    }

    private void sort(List<T> list, int from) {
        T min = list.get(from);
        int minIndex = from;
        for(int i = from; i < list.size(); i++) {
            T current = list.get(i);
            if(current.compareTo(min) < 0) {
                min = current;
                minIndex = i;
            }
        }
        swap(list, from, minIndex);
    }

    private void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
