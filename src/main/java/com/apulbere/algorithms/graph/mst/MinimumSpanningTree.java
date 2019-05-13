package com.apulbere.algorithms.graph.mst;

import com.apulbere.algorithms.graph.Graph;

/**
 * https://algs4.cs.princeton.edu/43mst/
 *
 */

public interface MinimumSpanningTree<V, C extends Number & Comparable<C>> {

    Graph<V, C> find(Graph<V, C> graph);
}
