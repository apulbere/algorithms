package com.apulbere.algorithms.graph;

import lombok.AllArgsConstructor;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class AdjacencyListWeightedGraph<V> {
    private Map<V, List<Edge<V>>> adjacencyList = new HashMap<>();

    public AdjacencyListWeightedGraph<V> addEdge(V source, int cost, V destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge<>(destination, cost));
        adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(new Edge<>(source, cost));
        return this;
    }

    /**
     * animation at https://algorithms.tutorialhorizon.com/dijkstras-shortest-path-algorithm-spt-adjacency-list-and-priority-queue-java-implementation/
     */
    public Map<V, Integer> dijkstraShortestPathCosts(V source) {
        var priorityQueue = new PriorityQueue<Edge<V>>();
        priorityQueue.add(new Edge<>(source, 0));

        var distances = new HashMap<V, Integer>();
        distances.put(source, 0);

        var visited = new HashSet<V>();

        while(!priorityQueue.isEmpty()) {
            var minEdge = priorityQueue.poll();
            if(visited.add(minEdge.endNode)) {
                for(Edge<V> edge: adjacencyList.get(minEdge.endNode)) {
                    int newDist = edge.cost + distances.get(minEdge.endNode);
                    if (newDist < distances.getOrDefault(edge.endNode, Integer.MAX_VALUE)) {
                        distances.put(edge.endNode, newDist);
                        priorityQueue.add(new Edge<>(edge.endNode, newDist));
                    }
                }
            }
        }
        return distances;
    }

    public List<Path<V>> dijkstraShortestPath(V source) {
        var priorityQueue = new PriorityQueue<Edge<V>>();
        priorityQueue.add(new Edge<>(source, 0));

        var distances = new HashMap<V, Integer>();
        distances.put(source, 0);

        var visited = new HashSet<V>();

        var predecessors = new HashMap<V, V>();

        while(!priorityQueue.isEmpty()) {
            var minEdge = priorityQueue.poll();
            if(visited.add(minEdge.endNode)) {
                for(Edge<V> edge: adjacencyList.get(minEdge.endNode)) {
                    int newDist = edge.cost + distances.get(minEdge.endNode);
                    if (newDist < distances.getOrDefault(edge.endNode, Integer.MAX_VALUE)) {
                        distances.put(edge.endNode, newDist);
                        predecessors.put(edge.endNode, minEdge.endNode);
                        priorityQueue.add(new Edge<>(edge.endNode, newDist));
                    }
                }
            }
        }

        return distances.entrySet().stream().map(entry -> Path.create(entry.getKey(), entry.getValue(), predecessors)).collect(toList());
    }

    public int dijkstraShortestPathCost(V source, V dest) {
        var priorityQueue = new PriorityQueue<Edge<V>>();
        var visited = new HashSet<V>();
        priorityQueue.add(new Edge<>(source, 0));

        while(!priorityQueue.isEmpty()) {
            var minEdge = priorityQueue.poll();
            if(visited.add(minEdge.endNode)) {
                if (minEdge.endNode.equals(dest)) {
                    return minEdge.cost;
                } else {
                    adjacencyList.get(minEdge.endNode).forEach(e -> priorityQueue.add(new Edge<>(e.endNode, minEdge.cost + e.cost)));
                }
            }
        }
        throw new PathNotFoundException();
    }

    public Path<V> dijkstraShortestPath(V source, V dest) {
        var priorityQueue = new PriorityQueue<Edge<V>>();
        priorityQueue.add(new Edge<>(source, 0));
        var distances = new HashMap<V, Integer>();
        distances.put(source, 0);
        var predecessors = new HashMap<V, V>();

        while(!priorityQueue.isEmpty()) {
            var minEdge = priorityQueue.poll();
            if(minEdge.endNode.equals(dest)) {
                break;
            }
            for(Edge<V> edge: adjacencyList.get(minEdge.endNode)) {
                int newCost = minEdge.cost + edge.cost;
                if(newCost < distances.getOrDefault(edge.endNode, Integer.MAX_VALUE)) {
                    distances.put(edge.endNode, newCost);
                    predecessors.put(edge.endNode, minEdge.endNode);
                    priorityQueue.add(new Edge<>(edge.endNode, newCost));
                }
            }
        }
        var cost = distances.get(dest);
        if(cost != null) {
            return Path.create(dest, distances.get(dest), predecessors);
        }
        throw new PathNotFoundException();
    }

    static class PathNotFoundException extends RuntimeException {}

    @AllArgsConstructor
    private static class Edge<V> implements Comparable<Edge<V>> {
        V endNode;
        int cost;

        @Override
        public int compareTo(Edge<V> o) {
            return cost - o.cost;
        }
    }
}
