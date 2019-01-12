package com.apulbere.collections.sort;

import java.util.ArrayList;
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
        int leftIndex = 0, rightIndex = 0;
        var result = new ArrayList<T>(left.size() + right.size());
        while(leftIndex < left.size() && rightIndex < right.size()) {
            T t1 = left.get(leftIndex);
            T t2 = right.get(rightIndex);
            if(t1.compareTo(t2) <= 0) {
                result.add(t1);
                leftIndex++;
            } else {
                result.add(t2);
                rightIndex++;
            }
        }
        while(leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }
        while(rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }
        return result;
    }
}
