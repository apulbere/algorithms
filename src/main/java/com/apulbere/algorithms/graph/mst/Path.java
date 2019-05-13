package com.apulbere.algorithms.graph.mst;

import static java.util.stream.Collectors.toList;

import com.apulbere.algorithms.graph.Edge;
import com.apulbere.algorithms.graph.Graph;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Path<V, C extends Number & Comparable<C>> {
    private C cost;
    private List<V> edgeSequence;

    public Path(Graph<V, C> graph, C zero, BinaryOperator<C> costOp) {
        this.cost = graph.getEdges().stream().map(Edge::getCost).reduce(zero, costOp);
        this.edgeSequence = graph.getEdges().stream()
                .flatMap(e -> Stream.of(e.getSource(), e.getDest()))
                .distinct()
                .collect(toList());
    }


}
