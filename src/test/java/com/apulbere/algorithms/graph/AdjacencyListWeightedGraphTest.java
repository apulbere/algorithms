package com.apulbere.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdjacencyListWeightedGraphTest {

    /**
     * https://www.geeksforgeeks.org//wp-content/uploads/Fig-11.jpg
     */
    @Test
    void dijkstraShortestPathCosts() {
        var graph = new AdjacencyListWeightedGraph<>()
                .addEdge(0, 4, 1)
                .addEdge(0, 8, 7)
                .addEdge(1, 8, 2)
                .addEdge(1, 11, 7)
                .addEdge(7, 7, 8)
                .addEdge(7, 1, 6)
                .addEdge(2, 2, 8)
                .addEdge(2, 7, 3)
                .addEdge(2, 4, 5)
                .addEdge(8, 6, 6)
                .addEdge(6, 2, 5)
                .addEdge(3, 14, 5)
                .addEdge(3, 9, 4)
                .addEdge(5, 10, 4);

        assertEquals(Map.of(0, 0,
                1, 4,
                2, 12,
                3, 19,
                4, 21,
                5, 11,
                6, 9,
                7, 8,
                8, 14), graph.dijkstraShortestPathCosts(0));
    }

    @Test
    void dijkstraShortestPath() {
        var graph = new AdjacencyListWeightedGraph<>()
                .addEdge(0, 4, 1)
                .addEdge(0, 8, 7)
                .addEdge(1, 8, 2)
                .addEdge(1, 11, 7)
                .addEdge(7, 7, 8)
                .addEdge(7, 1, 6)
                .addEdge(2, 2, 8)
                .addEdge(2, 7, 3)
                .addEdge(2, 4, 5)
                .addEdge(8, 6, 6)
                .addEdge(6, 2, 5)
                .addEdge(3, 14, 5)
                .addEdge(3, 9, 4)
                .addEdge(5, 10, 4);

        assertThat(graph.dijkstraShortestPath(0), containsInAnyOrder(
            new Path<>(0, List.of(0)),
            new Path<>(4, List.of(0, 1)),
            new Path<>(12, List.of(0, 1, 2)),
            new Path<>(19, List.of(0, 1, 2, 3)),
            new Path<>(21, List.of(0, 7, 6, 5, 4)),
            new Path<>(11, List.of(0, 7, 6, 5)),
            new Path<>(9, List.of(0, 7, 6)),
            new Path<>(8, List.of(0, 7)),
            new Path<>(14, List.of(0, 1, 2, 8))
        ));
    }

    @Test
    void dijkstraShortestPathCostFromTo() {
        var graph = new AdjacencyListWeightedGraph<>()
                .addEdge(0, 4, 1)
                .addEdge(0, 8, 7)
                .addEdge(1, 8, 2)
                .addEdge(1, 11, 7)
                .addEdge(7, 7, 8)
                .addEdge(7, 1, 6)
                .addEdge(2, 2, 8)
                .addEdge(2, 7, 3)
                .addEdge(2, 4, 5)
                .addEdge(8, 6, 6)
                .addEdge(6, 2, 5)
                .addEdge(3, 14, 5)
                .addEdge(3, 9, 4)
                .addEdge(5, 10, 4);

        assertEquals(4, graph.dijkstraShortestPathCost(0, 1));
        assertEquals(14, graph.dijkstraShortestPathCost(0, 8));
        assertEquals(16, graph.dijkstraShortestPathCost(8, 4));
    }

    @Test
    void dijkstraShortestPathFromToNotFound() {
        var graph = new AdjacencyListWeightedGraph<>().addEdge("A", 2, "C");
        assertThrows(AdjacencyListWeightedGraph.PathNotFoundException.class, () -> graph.dijkstraShortestPathCost("A", "B"));
    }
}
