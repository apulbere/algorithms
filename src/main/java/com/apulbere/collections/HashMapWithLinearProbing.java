package com.apulbere.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static java.util.Arrays.stream;

@NoArgsConstructor
public class HashMapWithLinearProbing<K, V> {
    private static final double LOAD_FACTOR = 0.7;

    @Getter
    private int size = 0;
    private int bucketCapacity = 10;
    private Object[] bucket = new Object[bucketCapacity];


    public HashMapWithLinearProbing(int initialSize) {
        this.bucketCapacity = initialSize;
        this.bucket = new Object[bucketCapacity];
    }

    public void put(K key, V value) {
        put(new HashNode<>(key, value), bucket);
        size++;

        if((size * 1.0) / bucketCapacity >= LOAD_FACTOR) {
            increaseCapacity();
        }
    }

    public V get(K key) {
        Integer bucketIndex = getBucketIndex(index(key), key);
        if(bucketIndex != null) {
            return ((HashNode<K, V>) bucket[bucketIndex]).value;
        }
        return null;
    }

    public V remove(K key) {
        Integer bucketIndex = getBucketIndex(index(key), key);
        if(bucketIndex != null) {
            //TODO: decrease capacity
            V value = ((HashNode<K, V>) bucket[bucketIndex]).value;
            bucket[bucketIndex] = null;
            size--;
            return value;
        }
        return null;
    }

    private Integer getBucketIndex(int index, K key) {
        Integer bucketIndex = getBucketIndex(index, bucketCapacity, key);
        return bucketIndex != null ? bucketIndex : getBucketIndex(0, index, key);
    }

    private Integer getBucketIndex(int start, int end, K key) {
        for(int i = start; i < end; i++) {
            var hashNode = (HashNode<K, V>) bucket[i];
            if(hashNode != null && hashNode.key.equals(key)) {
                return i;
            }
        }
        return null;
    }

    private void increaseCapacity() {
        bucketCapacity *= 2;
        var newBucket = new Object[bucketCapacity];
        stream(bucket).filter(Objects::nonNull).forEach(node -> put((HashNode<K, V>) node, newBucket));
        bucket = newBucket;
    }

    private void put(HashNode<K, V> hashNode, Object[] bucket) {
        int index = index(hashNode.key);
        if(!put(index, bucket.length, hashNode, bucket)) {
            put(0, index, hashNode, bucket);
        }
    }

    private boolean put(int start, int end, HashNode<K, V> hashNode, Object[] bucket) {
        for(int i = start; i < end; i++) {
            if(bucket[i] == null) {
                bucket[i] = hashNode;
                return true;
            }
        }
        return false;
    }

    private int index(K key) {
        return Math.abs(key.hashCode() % bucketCapacity);
    }

    @AllArgsConstructor
    private static class HashNode<K, V> {
        K key;
        V value;
    }
}
