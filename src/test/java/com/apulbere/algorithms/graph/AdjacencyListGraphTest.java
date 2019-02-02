package com.apulbere.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class AdjacencyListGraphTest {

    @Test
    void addEdge() {
        var expectedAdjacencyList = Map.of(
                1, List.of(4),
                2, List.of(4, 5),
                3, List.of(5),
                4, List.of(1, 2, 5),
                5, List.of(2, 3, 4)
        );

        var graph = new AdjacencyListGraph<Integer>();
        graph.addEdge(1, 4);
        graph.addEdge(4, 2);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        graph.addEdge(3, 5);
        var graphAdjacencyList = graph.getAdjacencyList();

        expectedAdjacencyList.forEach((k, v) -> assertThat(v, containsInAnyOrder(graphAdjacencyList.get(k).toArray())));
    }

    /**
     * https://www.tutorialspoint.com/data_structures_algorithms/images/depth_first_traversal.jpg
     */
    @Test
    void depthTraversal() {
        var graph = new AdjacencyListGraph<String>();
        graph.addEdge("S", "A");
        graph.addEdge("S", "B");
        graph.addEdge("S", "C");
        graph.addEdge("A", "D");
        graph.addEdge("D", "G");
        graph.addEdge("B", "E");
        graph.addEdge("E", "G");
        graph.addEdge("C", "F");
        graph.addEdge("F", "G");

        var expectedTraversalResult = List.of("S", "C", "F", "G", "E", "B", "D", "A");
        assertIterableEquals(expectedTraversalResult, graph.depthFirstTraversal("S"));
    }

    @Test
    void breadthTraversal() {
        var graph = new AdjacencyListGraph<String>();
        graph.addEdge("S", "A");
        graph.addEdge("S", "B");
        graph.addEdge("S", "C");
        graph.addEdge("A", "D");
        graph.addEdge("D", "G");
        graph.addEdge("B", "E");
        graph.addEdge("E", "G");
        graph.addEdge("C", "F");
        graph.addEdge("F", "G");

        var expectedTraversalResult = List.of("S", "A", "B", "C", "D", "E", "F", "G");
        assertIterableEquals(expectedTraversalResult, graph.breadthFirstSearch("S"));
    }
}
