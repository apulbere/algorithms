package com.apulbere.algorithms.graph;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
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

    @Test
    void breadthFirstTraversal() {
        var graph = new AdjacencyMatrixGraph<Integer>(6)
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(1, 3)
                .addEdge(2, 3)
                .addEdge(2, 4)
                .addEdge(3, 5);

        assertEquals(List.of(0, 1, 2, 3, 4, 5), graph.breadthFirstTraversal(0));
        assertEquals(List.of(3, 1, 2, 5, 0, 4), graph.breadthFirstTraversal(3));
    }

    /**
     * https://www.tutorialspoint.com/data_structures_algorithms/images/breadth_first_traversal.jpg
     */
    @Test
    void breadthFirstTraversal1() {
        var graph = new AdjacencyMatrixGraph<String>(8)
                .addEdge("S", "A")
                .addEdge("S", "B")
                .addEdge("S", "C")
                .addEdge("A", "D")
                .addEdge("D", "G")
                .addEdge("B", "E")
                .addEdge("E", "G")
                .addEdge("C", "F")
                .addEdge("F", "G");

        assertEquals(List.of("S", "A", "B", "C", "D", "E", "F", "G"), graph.breadthFirstTraversal("S"));
        assertEquals(List.of("G", "D", "E", "F", "A", "B", "C", "S"), graph.breadthFirstTraversal("G"));
        assertEquals(List.of(), graph.breadthFirstTraversal("NOT_IN_GRAPH"));
    }

    @Test
    void depthFirstTraversal1() {
        var graph = new AdjacencyMatrixGraph<String>(8)
                .addEdge("S", "A")
                .addEdge("S", "B")
                .addEdge("S", "C")
                .addEdge("A", "D")
                .addEdge("D", "G")
                .addEdge("B", "E")
                .addEdge("E", "G")
                .addEdge("C", "F")
                .addEdge("F", "G");

        assertEquals(List.of("S", "C", "F", "G", "E", "B", "D", "A"), graph.depthFirstTraversal("S"));
        assertEquals(List.of("G", "F", "C", "S", "B", "E", "A", "D"), graph.depthFirstTraversal("G"));
        assertEquals(List.of(), graph.depthFirstTraversal("TACA"));
    }
}
