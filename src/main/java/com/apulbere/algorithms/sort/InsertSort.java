package com.apulbere.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

public class InsertSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * STEP 1: if it is the first element, it is already sorted
     * STEP 2: pick next element
     * STEP 3: shift each element in the sublist one place to the left until a suitable position is found
     * STEP 4: repeat until the end of the list
     *
     * Performance:
     *  best    O(n)
     *  avg     O(n^2)
     *  worst   O(n^2)
     *
     * Space complexity: O(n)
     */
    @Override
    public List<T> sort(List<T> list) {
        var result = new ArrayList<>(list);
        for(int i = 0; i < result.size(); i++) {
            sort(result, i);
        }
        return result;
    }

    private void sort(List<T> list, int i) {
        for(; i > 0; i--) {
            if(list.get(i).compareTo(list.get(i - 1)) < 0) {
                swap(list, i, i - 1);
            }
        }
    }

    private void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
