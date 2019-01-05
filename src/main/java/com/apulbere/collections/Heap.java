package com.apulbere.collections;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Heap<T extends Comparable<T>> {
    private List<T> data;

    /**
     * for tests ONLY
     */
    Heap(List<T> data) {
        this.data = new ArrayList<>(data);
    }

    public Heap() {
        data = new ArrayList<>();
    }

    public void add(T value) {
        int pos = data.size();
        data.add(null);
        for(int parent = (pos - 1) / 2; pos > 0 && value.compareTo(data.get(parent)) < 0; pos = (pos - 1) / 2, parent = (pos - 1) / 2) {
            data.set(pos, data.get(parent));
        }
        data.set(pos, value);
    }

    public T min() {
        return data.isEmpty() ? null : data.get(0);
    }

    public void removeMin() {
        if(!data.isEmpty()) {
            int last = data.size() - 1;
            data.set(0, data.get(last));
            data.remove(last);
            shiftDown(0);
        }
    }

    private void shiftDown(int i) {
        int last = data.size() - 1;
        T left = last <= i + 1 ? null : data.get(i + 1);
        T right = last <= i + 2 ? null : data.get(i + 2);
        if (left != null || right != null) {
            T current = data.get(i);
            if(left != null && right == null) {
                if(left.compareTo(current) < 0) {
                    shift(i, i + 1);
                }
            } else if(left == null) {
                if(right.compareTo(current) < 0) {
                    shift(i, i + 2);
                }
            } else {
                if(left.compareTo(right) < 0) {
                    shift(i, i + 1);
                } else {
                    shift(i, i + 2);
                }
            }
            shiftDown(i + 1);
        }
    }

    private void shift(int i, int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
}