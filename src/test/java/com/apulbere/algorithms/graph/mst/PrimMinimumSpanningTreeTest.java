package com.apulbere.algorithms.graph.mst;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apulbere.algorithms.graph.BasicAdjacencyListWeightedGraph;
import com.apulbere.algorithms.graph.Edge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrimMinimumSpanningTreeTest {

    @Test
    @DisplayName("Prim's minimum spanning tree")
    void mst1() {
        var graph = new BasicAdjacencyListWeightedGraph<Character, Integer>()
                .addEdge('A', 3, 'B')
                .addEdge('A', 2, 'C')
                .addEdge('B', 4, 'D')
                .addEdge('B', 1, 'C')
                .addEdge('C', 5, 'D');

        var mst = new PrimMinimumSpanningTree<Character, Integer>().find(graph);

        assertThat(mst.getEdges(), containsInAnyOrder(
                new Edge<>('A', 2, 'C'),
                new Edge<>('B', 1, 'C'),
                new Edge<>('B', 4, 'D')));

        assertEquals(7, mst.stream().map(Edge::getCost).reduce(0, Integer::sum));
    }

    /**
     * graph: https://www.geeksforgeeks.org/wp-content/uploads/Fig-0-300x139.jpg
     * expected: https://www.geeksforgeeks.org/wp-content/uploads/fig8new.jpeg
     */
    @Test
    @DisplayName("Prim's minimum spanning tree 2")
    void mst3() {
        var graph = new BasicAdjacencyListWeightedGraph<Integer, Integer>()
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

        var mst = new PrimMinimumSpanningTree<Integer, Integer>().find(graph);

        assertThat(mst.getEdges(), containsInAnyOrder(
                new Edge<>(0, 4, 1),
                new Edge<>(0, 8, 7),
                new Edge<>(7, 1, 6),
                new Edge<>(2, 2, 8),
                new Edge<>(6, 2, 5),
                new Edge<>(2, 4, 5),
                new Edge<>(2, 7, 3),
                new Edge<>(3, 9, 4)));

        assertEquals(37, mst.stream().map(Edge::getCost).reduce(0, Integer::sum));
    }
}
