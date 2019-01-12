package com.apulbere.collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class MergeSortOnSpaceComplexity<T extends Comparable<T>> implements Sort<T> {

    /**
     * Performance:
     *  best    O(n log n)
     *  avg     O(n log n)
     *  worst   O(n log n)
     *
     * Space complexity: O(n)
     */
    @Override
    public List<T> sort(List<T> list) {
        var result = new ArrayList<>(list);
        mergeSort(result, 0 , result.size() - 1);
        return result;
    }

    private void mergeSort(List<T> list, int left, int right) {
        if(left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(list, left, middle);
            mergeSort(list, middle + 1, right);
            merge(list, left, middle, right);
        }
    }

    private void merge(List<T> list, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        var tempLeft = IntStream.range(0, n1).mapToObj(i -> list.get(left + i)).collect(toList());
        var tempRight = IntStream.range(0, n2).mapToObj(i -> list.get(middle + 1 + i)).collect(toList());

        int i = 0, j = 0, k = left;
        while(i < n1 && j < n2) {
            T t1 = tempLeft.get(i);
            T t2 = tempRight.get(j);
            if(t1.compareTo(t2) <= 0) {
                list.set(k, t1);
                i++;
            } else {
                list.set(k, t2);
                j++;
            }
            k++;
        }
        while(i < n1) {
            list.set(k++, tempLeft.get(i++));
        }
        while(j < n2) {
            list.set(k++, tempRight.get(j++));
        }
    }
}
