package com.apulbere.algorithms.graph;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
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
        int index = mapper.get(node);

        var inverseMapper = mapper.entrySet().stream().collect(toMap(Map.Entry::getValue, Map.Entry::getKey));
        return IntStream.range(0, size).filter(i -> matrix[index][i] != 0).mapToObj(inverseMapper::get).collect(toList());
    }
}
