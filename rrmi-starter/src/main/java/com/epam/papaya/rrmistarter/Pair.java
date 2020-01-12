package com.epam.papaya.rrmistarter;

/**
 * @author Evgeny Borisov
 */
public class Pair<K,V> {
    private final K k;
    private final V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K _1() {
        return k;
    }

    public V _2() {
        return v;
    }
}
