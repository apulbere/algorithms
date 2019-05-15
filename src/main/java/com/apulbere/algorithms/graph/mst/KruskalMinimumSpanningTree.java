package com.apulbere.algorithms.graph.mst;

import com.apulbere.algorithms.graph.BasicAdjacencyListWeightedGraph;
import com.apulbere.algorithms.graph.Edge;
import com.apulbere.algorithms.graph.Graph;
import com.apulbere.algorithms.structure.DisjointSetOptimized;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalMinimumSpanningTree<V, C extends Number & Comparable<C>> implements MinimumSpanningTree<V, C> {

    /**
     * 1. Sort all the edges in non-decreasing order of their weight.
     * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
     * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
     */
    @Override
    public Graph<V, C> find(Graph<V, C> graph) {
        var edges = new PriorityQueue<Edge<V, C>>(Comparator.comparing(Edge::getCost));
            edges.addAll(graph.getEdges());
        var disjointSet = new DisjointSetOptimized<V>();
        var mstEdges = new LinkedList<Edge<V, C>>();

        while(!edges.isEmpty()) {
            var min = edges.poll();
            if(!disjointSet.connected(min.getSource(), min.getDest())) {
                disjointSet.union(min.getSource(), min.getDest());
                mstEdges.add(min);
            }
        }

        return new BasicAdjacencyListWeightedGraph<>(mstEdges);
    }

}
