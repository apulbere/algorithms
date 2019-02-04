package com.apulbere.algorithms.graph;

import com.apulbere.algorithms.graph.AdjacencyListGraph.Path;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        var graph = new AdjacencyListGraph<Integer>()
            .addEdge(1, 4)
            .addEdge(4, 2)
            .addEdge(4, 5)
            .addEdge(5, 2)
            .addEdge(3, 5);
        var graphAdjacencyList = graph.getAdjacencyList();

        expectedAdjacencyList.forEach((k, v) -> assertThat(v, containsInAnyOrder(graphAdjacencyList.get(k).toArray())));
    }

    /**
     * https://www.tutorialspoint.com/data_structures_algorithms/images/depth_first_traversal.jpg
     */
    @Test
    void depthTraversal() {
        var graph = new AdjacencyListGraph<String>()
            .addEdge("S", "A")
            .addEdge("S", "B")
            .addEdge("S", "C")
            .addEdge("A", "D")
            .addEdge("D", "G")
            .addEdge("B", "E")
            .addEdge("E", "G")
            .addEdge("C", "F")
            .addEdge("F", "G");

        var expectedTraversalResult = List.of("S", "C", "F", "G", "E", "B", "D", "A");
        assertIterableEquals(expectedTraversalResult, graph.depthFirstTraversal("S"));
    }

    @Test
    void breadthTraversal() {
        var graph = new AdjacencyListGraph<String>()
            .addEdge("S", "A")
            .addEdge("S", "B")
            .addEdge("S", "C")
            .addEdge("A", "D")
            .addEdge("D", "G")
            .addEdge("B", "E")
            .addEdge("E", "G")
            .addEdge("C", "F")
            .addEdge("F", "G");

        var expectedTraversalResult = List.of("S", "A", "B", "C", "D", "E", "F", "G");
        assertIterableEquals(expectedTraversalResult, graph.breadthFirstSearch("S"));
    }

    /**
     * A----B----C-----F
     * \               \
     * \               \
     * D---------E-----G
     */
    @Test
    void shortestPathBFS() {
        var graph = new AdjacencyListGraph<String>()
            .addEdge("A", "B")
            .addEdge("B", "C")
            .addEdge("C", "E")
            .addEdge("E", "D")
            .addEdge("D", "A");

        assertEquals(new Path<>(2, List.of("A", "D", "E")), graph.shortestPathBFS("A", "E"));
    }

    /**
     * https://cdncontribute.geeksforgeeks.org/wp-content/uploads/exampleFigure-1.png
     */
    @Test
    void shortestPathBFS1() {
        var graph = new AdjacencyListGraph<Integer>()
            .addEdge(2, 1)
            .addEdge(1, 0)
            .addEdge(0, 3)
            .addEdge(3, 4)
            .addEdge(3, 7)
            .addEdge(4, 5)
            .addEdge(4, 7)
            .addEdge(4, 6)
            .addEdge(7, 6)
            .addEdge(5, 6);

        assertEquals(new Path<>(2, List.of(0, 3, 7)), graph.shortestPathBFS(0, 7));
    }

    @Test
    void shortestPathBFS2() {
        var graph = new AdjacencyListGraph<>()
                .addEdge("A","B")
                .addEdge("B", "C")
                .addEdge("C", "D")
                .addEdge("D", "E")
                .addEdge("E", "F");

        var expectedPath = new Path<>(5, List.of("A", "B", "C", "D", "E", "F"));
        assertEquals(expectedPath, graph.shortestPathBFS("A", "F"));
    }

    @Test
    void shortestPathBFSNotFound() {
        var graph = new AdjacencyListGraph<Integer>()
                .addEdge(2, 1)
                .addEdge(1, 0)
                .addEdge(0, 3)
                .addEdge(3, 4)
                .addEdge(3, 7)
                .addEdge(4, 5)
                .addEdge(4, 7)
                .addEdge(4, 6)
                .addEdge(7, 6)
                .addEdge(5, 6);

        assertEquals(Path.EMPTY, graph.shortestPathBFS(0, 899));
        assertEquals(Path.EMPTY, new AdjacencyListGraph<>().shortestPathBFS("C", "S"));
    }
}
