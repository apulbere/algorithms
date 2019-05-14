package com.apulbere.algorithms.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * disjoint-set / union-find
 */

public class DisjointSet<V> {
    protected Map<V, V> parent = new HashMap<>();

    protected V find(V val) {
        V p = parent.get(val);
        return p == null ? val : find(p);
    }

    public void union(V v1, V v2) {
        V v1p = find(v1);
        V v2p = find(v2);
        parent.put(v1p, v2p);
    }

    public boolean connected(V v1, V v2) {
        return find(v1).equals(find(v2));
    }
}
