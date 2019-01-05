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
}