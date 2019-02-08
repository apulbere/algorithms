package com.apulbere.algorithms.graph;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import org.junit.jupiter.api.Test;

class AdjacencyMatrixGraphTest {

    @Test
    void correctAdjacencyMatrix() {
        var graph = new AdjacencyMatrixGraph<Integer>(5)
                .addEdge(0, 1)
                .addEdge(0, 4)
                .addEdge(1, 4)
                .addEdge(1, 3)
                .addEdge(1, 2)
                .addEdge(3, 4)
                .addEdge(3, 2);
        assertThat(graph.findAdjacentNodes(0), containsInAnyOrder(1, 4));
        assertThat(graph.findAdjacentNodes(1), containsInAnyOrder(0, 2, 3, 4));
        assertThat(graph.findAdjacentNodes(2), containsInAnyOrder(1, 3));
        assertThat(graph.findAdjacentNodes(3), containsInAnyOrder(1, 2, 4));
        assertThat(graph.findAdjacentNodes(4), containsInAnyOrder(1, 3, 0));
    }
}
