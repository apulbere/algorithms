package com.apulbere.algorithms.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Edge<T, V extends Number & Comparable<V>> {
    private T source;
    private V cost;
    private T dest;
}
