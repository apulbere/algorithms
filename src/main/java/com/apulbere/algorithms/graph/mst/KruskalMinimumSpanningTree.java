package com.apulbere.algorithms.graph.mst;

import com.apulbere.algorithms.graph.Edge;
import com.apulbere.algorithms.graph.Graph;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KruskalMinimumSpanningTree<V, C extends Number & Comparable<C>> implements MinimumSpanningTree<V, C> {

    @Override
    public Graph<V, C> find(Graph<V, C> graph) {
        var edges = new PriorityQueue<Edge<V, C>>(Comparator.comparing(Edge::getCost));

        while(!edges.isEmpty()) {
            var min = edges.poll();

        }

        throw new UnsupportedOperationException("Legen ... Wait for it ...");
    }

}
