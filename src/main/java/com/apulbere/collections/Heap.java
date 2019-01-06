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
            if(!data.isEmpty()) {
                shiftDown();
            }
        }
    }

    private void shiftDown() {
        T tmp = data.get(0);
        int parent = 0;
        for(int child = 1; parent * 2 + 1 < data.size(); parent = child, child = child * 2 + 1) {

            if(child + 1 < data.size()
                    && data.get(child).compareTo(data.get(child + 1)) > 0) {
                child++;
            }

            if(tmp.compareTo(data.get(child)) > 0) {
                data.set(parent, data.get(child));
            } else {
                break;
            }
        }
        data.set(parent, tmp);
    }
}