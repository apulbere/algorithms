package com.apulbere.algorithms.graph.mst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apulbere.algorithms.graph.BasicAdjacencyListWeightedGraph;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KruskalMinimumSpanningTreeTest {


    @Test
    @DisplayName("Kruskal's minimum spanning tree")
    void mst1() {
        var graph = new BasicAdjacencyListWeightedGraph<Character, Integer>()
                .addEdge('A', 3, 'B')
                .addEdge('A', 2, 'C')
                .addEdge('B', 4, 'D')
                .addEdge('B', 1, 'C')
                .addEdge('C', 5, 'D');

        var mst = new KruskalMinimumSpanningTree<Character, Integer>().find(graph);

        var path = new Path<>(mst, 0, Integer::sum);
        var expectedPath = new Path<>(7, List.of('B', 'C', 'A', 'D'));

        assertEquals(expectedPath, path);
    }

    @Test
    @DisplayName("Kruskal's minimum spanning tree for null graph")
    void mst2() {
        var graph = new BasicAdjacencyListWeightedGraph<Character, Integer>();
        var mst = new KruskalMinimumSpanningTree<Character, Integer>().find(graph);

        var path = new Path<>(mst, 0, Integer::sum);
        var expectedPath = new Path<>(0, List.of());

        assertEquals(expectedPath, path);
    }

}
