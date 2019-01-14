package com.apulbere.algorithms.sort;

import java.util.List;

@FunctionalInterface
public interface Sort<T extends Comparable<T>> {

    List<T> sort(List<T> list);
}