package com.apulbere.algorithms.graph;

import lombok.Getter;
import lombok.Value;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class AdjacencyListGraph<V> {
    @Getter
    private Map<V, List<V>> adjacencyList = new HashMap<>();

    public AdjacencyListGraph<V> addEdge(V source, V destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
        return this;
    }

    public Collection<V> depthFirstTraversal(V startVertex) {
        var stack = new LinkedList<V>();
        var result = new LinkedHashSet<V>();
        stack.push(startVertex);
        while(!stack.isEmpty()) {
            V vertex = stack.pop();
            if(result.add(vertex)) {
                result.add(vertex);
                adjacencyList.getOrDefault(vertex, emptyList()).forEach(stack::push);
            }
        }
        return result;
    }

    public Collection<V> breadthFirstTraversal(V startVertex) {
        var stack = new LinkedList<V>();
        var result = new LinkedHashSet<V>();
        stack.push(startVertex);
        while(!stack.isEmpty()) {
            V vertex = stack.pop();
            result.add(vertex);
            adjacencyList.getOrDefault(vertex, emptyList()).stream().filter(result::add).forEach(stack::add);
        }
        return result;
    }

    /**
     * time complexity O(V + E), where V is the number of vertices, and E is the number of Edges
     * it means, whichever term is bigger will dominate the time complexity
     */
    public Path<V> shortestPathBFS(V source, V destination) {
        var visited = new HashSet<V>();
        var stack = new LinkedList<V>();
        var predecessors = new HashMap<V, V>();
        var distances = new HashMap<V, Integer>();
        stack.push(source);
        visited.add(source);
        while(!stack.isEmpty()) {
            V vertex = stack.pop();
            for(V nextVertex: adjacencyList.getOrDefault(vertex, emptyList())) {
                if(visited.add(nextVertex)) {
                    predecessors.put(nextVertex, vertex);
                    distances.put(nextVertex, distances.getOrDefault(vertex, 0) + 1);
                    stack.add(nextVertex);
                }
                if(vertex.equals(destination)) {
                    break;
                }
            }
        }

        if(predecessors.get(destination) == null) {
            return Path.EMPTY;
        }

        var pathList = Stream.iterate(destination, Objects::nonNull, predecessors::get)
                .collect(addAlwaysFirstCollector());
        return new Path<>(distances.get(destination), pathList);
    }

    private Collector<V, LinkedList<V>, LinkedList<V>> addAlwaysFirstCollector() {
        return Collector.of(LinkedList::new,
                LinkedList::addFirst,
                (a, b) -> { throw new UnsupportedOperationException("parallel processing not supported"); });
    }

    @Value
    public static class Path<V> {
        private int distance;
        private Collection<V> path;

        public static final Path EMPTY = new Path<>(0, emptyList());
    }
}
