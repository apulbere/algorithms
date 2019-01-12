package com.apulbere.collections.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

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

        var tempLeft = IntStream.range(0, n1).mapToObj(i -> list.get(left + i)).collect(toCollection(LinkedList::new));
        var tempRight = IntStream.rangeClosed(1, n2).mapToObj(i -> list.get(middle + i)).collect(toCollection(LinkedList::new));

        int k = left;
        for(; !tempLeft.isEmpty() && !tempRight.isEmpty(); k++) {
            if(tempLeft.peek().compareTo(tempRight.peek()) <= 0) {
                list.set(k, tempLeft.pop());
            } else {
                list.set(k, tempRight.pop());
            }
        }
        while(!tempLeft.isEmpty()) {
            list.set(k++, tempLeft.pop());
        }
        while(!tempRight.isEmpty()) {
            list.set(k++, tempRight.pop());
        }
    }
}
