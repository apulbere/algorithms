package com.apulbere.algorithms.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * disjoint-set / union-find
 */

public class DisjointSet<V> {
    private Map<V, V> parent = new HashMap<>();

    private V find(V val) {
        V p = parent.get(val);
        return p == null ? val : find(p);
    }

    public void union(V val1, V val2) {
        V v1p = find(val1);
        V v2p = find(val2);
        parent.put(v1p, v2p);
    }

    public boolean connected(V val1, V val2) {
        return find(val1).equals(find(val2));
    }
}
