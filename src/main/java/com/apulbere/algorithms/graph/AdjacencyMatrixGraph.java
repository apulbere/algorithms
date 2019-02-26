package com.apulbere.algorithms.graph;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class AdjacencyMatrixGraph<V> {
    private Map<V, Integer> mapper = new HashMap<>();
    private int[][] matrix;
    private int vertexIndex;
    private int size;

    public AdjacencyMatrixGraph(int size) {
        matrix = new int[size][size];
        this.size = size;
    }

    public AdjacencyMatrixGraph<V> addEdge(V source, V dest) {
        Integer sourceIndex = mapper.computeIfAbsent(source, k -> vertexIndex++);
        Integer destIndex = mapper.computeIfAbsent(dest, k -> vertexIndex++);
        matrix[sourceIndex][destIndex] = matrix[destIndex][sourceIndex] = 1;
        mapper.put(source, sourceIndex);
        mapper.put(dest, destIndex);
        return this;
    }

    public List<V> findAdjacentNodes(V node) {
        var index = mapper.get(node);
        if(index != null) {
            var inverseMapper = inverseMapper();
            return IntStream.range(0, size).filter(i -> matrix[index][i] != 0).mapToObj(inverseMapper::get).collect(toList());
        }
        return emptyList();
    }

    public Collection<V> breadthFirstTraversal(V startVertex) {
        var startIndex = mapper.get(startVertex);
        if(startIndex != null) {
            var stack = new LinkedList<Integer>();
            var result = new LinkedHashSet<Integer>();

            stack.push(startIndex);
            result.add(startIndex);
            while (!stack.isEmpty()) {
                int vertex = stack.pop();
                IntStream.range(0, size).filter(i -> matrix[vertex][i] != 0).filter(result::add).forEach(stack::add);
            }
            var inverseMapper = inverseMapper();
            return result.stream().map(inverseMapper::get).collect(toList());
        }
        return emptyList();
    }

    public Collection<V> depthFirstTraversal(V startVertex) {
        var startIndex = mapper.get(startVertex);
        if(startIndex != null) {
            var stack = new LinkedList<Integer>();
            var beenThereDoneThat = new HashSet<Integer>();
            var result = new LinkedList<Integer>();

            stack.push(startIndex);
            while(!stack.isEmpty()) {
                int vertex = stack.pop();
                if(beenThereDoneThat.add(vertex)) {
                    result.add(vertex);
                    IntStream.range(0, size).filter(i -> matrix[vertex][i] != 0).forEach(stack::push);
                }
            }
            var inverseMapper = inverseMapper();
            return result.stream().map(inverseMapper::get).collect(toList());
        }
        return emptyList();
    }

    private Map<Integer, V> inverseMapper() {
        return mapper.entrySet().stream().collect(toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
