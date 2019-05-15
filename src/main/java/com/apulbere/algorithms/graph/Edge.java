package com.apulbere.algorithms.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Edge<T, V extends Number & Comparable<V>> {
    private T source;
    private V cost;
    private T dest;

    public T either() {
        return source;
    }

    public T other(T vertex) {
        if(vertex == source) { //yes '==' not 'equals'
            return dest;
        } else if(vertex == dest) {
            return source;
        }
        throw new IllegalArgumentException("given vertex does not belong to edge");
    }
}
