package com.apulbere.algorithms.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * disjoint-set / union-find with path compression
 */

public class DisjointSetOptimized<V> extends DisjointSet<V> {
    private Map<V, Integer> rank = new HashMap<>();

    @Override
    public void union(V v1, V v2) {
        V v1p = find(v1);
        V v2p = find(v2);

        int v1Rank = rank.getOrDefault(v1, 0);
        int v2Rank = rank.getOrDefault(v2, 0);

        if(v1Rank < v2Rank) {
            parent.put(v1p, v2p);
        } else if(v1Rank > v2Rank) {
            parent.put(v2p, v1p);
        } else {
            parent.put(v1p, v2p);
            rank.put(v2p, ++v2Rank);
        }
    }
}
