package com.company.cache;

import com.company.strategies.EvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl<Key, Value> implements CacheInterface<Key,Value> {

    Map<Key,Value> keyValueMap;
    EvictionPolicy<Key> evictionPolicy;
    private final Integer size;

    public CacheImpl(Integer size, EvictionPolicy<Key> evictionPolicy) {
        this.size = size;
        keyValueMap = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public void put(Key key, Value value) {
        if(keyValueMap.size() == size) {
            Key evictKey = evictionPolicy.evictKey();
            keyValueMap.remove(evictKey);
        }
        evictionPolicy.accessKey(key);
        keyValueMap.put(key,value);
    }

    @Override
    public Value get(Key key) {
        if (keyValueMap.containsKey(key)) {
            Value val = keyValueMap.get(key);
            evictionPolicy.accessKey(key);
            return val;
        }
        return null;
    }

    @Override
    public void printAllInCache() {
        for(Key key : keyValueMap.keySet()) {
            System.out.println("key : " + key + " value : " + keyValueMap.get(key));
        }
    }
}
