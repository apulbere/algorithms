package com.apulbere.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSortHoarePartition<T extends Comparable<T>> implements Sort<T> {

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

    /**
     * works by initializing two indexes that start at two ends, the two indexes move toward each other until an inversion is found
     */
    private int partition(List<T> list, int low, int high) {
        T pivot = list.get(low);
        int start = low;
        int partition = high + 1;
        while(true) {
            while(list.get(++start).compareTo(pivot) < 0) {
                if(start == high) break;
            }
            while (list.get(--partition).compareTo(pivot) > 0) {
                if(partition == low) break;
            }
            if(start >= partition) {
                break;
            }
            swap(list, partition, start);
        }
        swap(list, partition, low);
        return partition;
    }

    void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
