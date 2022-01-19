package com.company.factory;

import com.company.cache.CacheImpl;
import com.company.cache.CacheInterface;
import com.company.multilevelcache.MultiLevelCacheImpl;
import com.company.multilevelcache.MultiLevelCacheInterface;
import com.company.strategies.EvictionPolicy;
import com.company.strategies.LRUEvictionPolicy;

import java.util.ArrayList;
import java.util.List;

public class MultiLevelCacheFactory<Key,Value> {

    public static<Key,Value> MultiLevelCacheInterface<Key,Value> getDefaultMultiLevelCache() {

        List<CacheInterface<Key,Value>> cacheList = new ArrayList<>();
        EvictionPolicy<Key> evictionPolicy = new LRUEvictionPolicy<>();

        CacheInterface<Key,Value> cacheLevel;
        cacheLevel = new CacheImpl<>(2,evictionPolicy);
        cacheList.add(cacheLevel);
        cacheLevel = new CacheImpl<>(3,evictionPolicy);
        cacheList.add(cacheLevel);
        cacheLevel = new CacheImpl<>(4,evictionPolicy);
        cacheList.add(cacheLevel);

        return new MultiLevelCacheImpl<>(cacheList);
    }
}
