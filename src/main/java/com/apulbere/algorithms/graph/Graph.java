package com.apulbere.algorithms.graph;

import java.util.Collection;

public interface Graph<V, C extends Number & Comparable<C>> {

    Collection<Edge<V, C>> getEdges();
}
