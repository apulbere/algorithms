package com.apulbere.algorithms.structure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class DisjointSetTest {

    /**
     * We are given 10 individuals say,
     * a, b, c, d, e, f, g, h, i, j
     *
     * Following are relationships to be added.
     * a <-> b
     * b <-> d
     * c <-> f
     * c <-> i
     * j <-> e
     * g <-> j
     *
     * And given queries like whether a is a friend of d
     * or not.
     *
     * We basically need to create following 4 groups
     * and maintain a quickly accessible connection
     * among group items:
     * G1 = {a, b, d}
     * G2 = {c, f, i}
     * G3 = {e, g, j}
     * G4 = {h}
     */
    @ParameterizedTest
    @MethodSource("disjointSetImpls")
    void checkRelations(DisjointSet<Character> disjointSet) {
        disjointSet.union('a', 'b');
        disjointSet.union('b', 'd');
        disjointSet.union('c', 'f');
        disjointSet.union('c', 'i');
        disjointSet.union('j', 'e');
        disjointSet.union('g', 'j');


        assertTrue(disjointSet.connected('a', 'd'));
        assertTrue(disjointSet.connected('c', 'f'));
        assertTrue(disjointSet.connected('c', 'i'));
        assertTrue(disjointSet.connected('e', 'g'));
        assertTrue(disjointSet.connected('e', 'j'));

        assertFalse(disjointSet.connected('a', 'c'));
        assertFalse(disjointSet.connected('b', 'c'));
        assertFalse(disjointSet.connected('e', 'i'));
    }

    private static Iterator<DisjointSet<Character>> disjointSetImpls() {
        return List.of(new DisjointSet<Character>(), new DisjointSetOptimized<Character>()).iterator();
    }
}
