package com.apulbere.algorithms;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HashMapWithLinearProbingTest {

    @RepeatedTest(10)
    void putAndGet() {
        var hashMap = new HashMapWithLinearProbing<String, String>(10);

        var strings = generateRandomString().limit(1990).collect(toList());
        strings.forEach(str -> hashMap.put(str, str + "-value"));

        strings.forEach(str -> assertEquals(hashMap.get(str), str + "-value"));
    }

    @RepeatedTest(10)
    void remove() {
        var hashMap = new HashMapWithLinearProbing<String, String>(10);

        var strings = generateRandomString().limit(1991).collect(toList());

        strings.forEach(str -> hashMap.put(str, str + "-value"));

        assertEquals(strings.get(5) + "-value", hashMap.get(strings.get(5)));
        assertEquals(1991, hashMap.getSize());

        hashMap.remove(strings.get(5));
        assertNull(hashMap.get(strings.get(5)));
        assertEquals(1990, hashMap.getSize());
    }

    private Stream<String> generateRandomString() {
        Random random = new Random();
        return Stream.generate(() -> random.ints('a', 'z')
                                        .limit(10)
                                        .collect(
                                                StringBuilder::new,
                                                StringBuilder::appendCodePoint,
                                                StringBuilder::append)
                                        .toString());
    }
}
