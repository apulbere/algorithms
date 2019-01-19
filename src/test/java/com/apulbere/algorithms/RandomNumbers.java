package com.apulbere.algorithms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomNumbers {

    public List<Integer> randomList() {
        return random().limit(randomBetween(0, 17)).collect(Collectors.toUnmodifiableList());
    }

    public Stream<Integer> random() {
        return IntStream.generate(() -> randomBetween(0, 100)).boxed();
    }

    public int randomBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
