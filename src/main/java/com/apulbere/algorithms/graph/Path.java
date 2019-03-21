package com.apulbere.algorithms.graph;

import lombok.Value;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

@Value
public class Path<V> {
    private int cost;
    private Collection<V> path;

    public static final Path EMPTY = new Path<>(0, emptyList());

    static <V> Path<V> create(V node, Integer cost, Map<V, V> predecessors) {
        var pathList = Stream.iterate(node, Objects::nonNull, predecessors::get).collect(addAlwaysFirstCollector());
        return new Path<>(cost, pathList);
    }

    private static <V> Collector<V, LinkedList<V>, LinkedList<V>> addAlwaysFirstCollector() {
        return Collector.of(LinkedList::new,
                LinkedList::addFirst,
                (a, b) -> { throw new UnsupportedOperationException("merge not implemented"); });
    }
}
