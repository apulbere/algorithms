package com.apulbere.algorithms.graph;

import java.util.Collection;
import java.util.stream.Stream;

public interface Graph<V, C extends Number & Comparable<C>> {

    Collection<Edge<V, C>> getEdges();

    default Stream<Edge<V, C>> stream() {
        return getEdges().stream();
    }
}
