package com.company.cache;

public interface CacheInterface<Key,Value> {

    void put(Key key,Value value);

    Value get(Key key);

    void printAllInCache();

}
