package com.company.strategies;

public interface EvictionPolicy<Key> {

    void accessKey(Key key);

    Key evictKey();
}
