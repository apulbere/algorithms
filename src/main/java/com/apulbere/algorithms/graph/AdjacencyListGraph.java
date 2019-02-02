package com.apulbere.algorithms.graph;

import lombok.Getter;

import java.util.*;

import static java.util.Collections.emptyList;

public class AdjacencyListGraph<V> {
    @Getter
    private Map<V, List<V>> adjacencyList = new HashMap<>();

    public void addEdge(V source, V destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
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

    public Collection<V> breadthFirstSearch(V startVertex) {
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
}
