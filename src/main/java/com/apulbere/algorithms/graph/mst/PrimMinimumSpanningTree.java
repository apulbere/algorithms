package com.apulbere.algorithms.graph.mst;

import com.apulbere.algorithms.graph.BasicAdjacencyListWeightedGraph;
import com.apulbere.algorithms.graph.Edge;
import com.apulbere.algorithms.graph.Graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class PrimMinimumSpanningTree<V, C extends Number & Comparable<C>> implements MinimumSpanningTree<V, C> {

    /**
     * 1. Start by picking any vertex to be the root of the tree
     * 2. While the tree does not contain all vertices in the graph find shortest edge leaving the tree and add it to the tree
     */

    @Override
    public Graph<V, C> find(Graph<V, C> graph) {
        var adjacencyMap = graph.stream()
             .flatMap(e -> Stream.of(new SimpleEntry<>(e.getSource(), e), new SimpleEntry<>(e.getDest(), e)))
             .collect(groupingBy(SimpleEntry::getKey, mapping(SimpleEntry::getValue, toCollection(LinkedList::new))));

        var mstEdges = new LinkedList<Edge<V, C>>();
        var pq = new PriorityQueue<Edge<V, C>>(comparing(Edge::getCost));
        var visited = new HashSet<V>();

        for(var entry: adjacencyMap.entrySet()) {
            scan(visited, pq, entry.getValue(), entry.getKey());
            while(!pq.isEmpty()) {
                var minEdge = pq.poll();

                V v1 = minEdge.either();
                V v2 = minEdge.other(v1);

                if(visited.contains(v1) && visited.contains(v2)) {
                    continue;
                }

                mstEdges.add(minEdge);

                scan(visited, pq, adjacencyMap.get(v1), v1);
                scan(visited, pq, adjacencyMap.get(v2), v2);
            }
        }

        return new BasicAdjacencyListWeightedGraph<>(mstEdges);
    }

    private void scan(Set<V> visited, PriorityQueue<Edge<V, C>> pq, List<Edge<V, C>> adjList, V vertex) {
        if(visited.add(vertex)) {
            adjList.stream().filter(e -> !visited.contains(e.other(vertex))).forEach(pq::add);
        }
    }
}
