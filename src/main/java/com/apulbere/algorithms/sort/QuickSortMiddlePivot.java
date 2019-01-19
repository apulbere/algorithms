package com.apulbere.algorithms.sort;

import java.util.List;

public class QuickSortMiddlePivot<T extends Comparable<T>> extends QuickSort<T> {

    @Override
    protected int partition(List<T> list, int low, int high) {
        T pivot = list.get((low + high) / 2);
        int i = low, j = high;
        while(i <= j) {
            while(list.get(i).compareTo(pivot) < 0) {
                i++;
            }
            while(list.get(j).compareTo(pivot) > 0) {
                j--;
            }
            if(i <= j) {
                swap(list, i++, j--);
            }
        }
        return i;
    }
}
