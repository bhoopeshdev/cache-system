package com.company.cache;

import com.company.dto.GetResponse;
import com.company.dto.PutResponse;
import com.company.strategies.EvictionPolicy;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

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
    public PutResponse<Key,Value> put(Key key, Value value) {
        PutResponse<Key,Value> putResponse = new PutResponse<>();
        if(keyValueMap.size() == size) {
            Key evictKey = evictionPolicy.evictKey();
            putResponse.setEvictedKey(evictKey);
            putResponse.setEvictedValue(keyValueMap.get(evictKey));
            keyValueMap.remove(evictKey);
        }
        evictionPolicy.accessKey(key);
        keyValueMap.put(key,value);
        putResponse.setSuccess(Boolean.TRUE);
        return putResponse;
    }

    @Override
    public GetResponse<Key,Value> get(Key key) {
        GetResponse<Key,Value> getResponse = new GetResponse<>();
        if (keyValueMap.containsKey(key)) {
            Value val = keyValueMap.get(key);
            evictionPolicy.accessKey(key);
            getResponse.setSuccess(Boolean.TRUE);
            getResponse.setKey(key);
            getResponse.setValue(val);
        }
        else {
            getResponse.setSuccess(Boolean.FALSE);
        }
        return getResponse;
    }

    @Override
    public void printAllInCache() {
        for(Key key : keyValueMap.keySet()) {
            System.out.println("key : " + key + " value : " + keyValueMap.get(key));
        }
    }
}
