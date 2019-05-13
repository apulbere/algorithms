package com.apulbere.algorithms.graph;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BasicAdjacencyListWeightedGraph<V, C extends Number & Comparable<C>> implements Graph<V, C> {

    @Getter
    private List<Edge<V, C>> edges;

    public BasicAdjacencyListWeightedGraph() {
        this.edges = new LinkedList<>();
    }

    public BasicAdjacencyListWeightedGraph<V, C> addEdge(V source, C cost, V dest) {
        edges.add(new Edge<>(source, cost, dest));
        return this;
    }

}