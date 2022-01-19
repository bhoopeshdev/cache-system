package com.company;

import com.company.cache.CacheImpl;
import com.company.cache.CacheInterface;
import com.company.dto.GetResponse;
import com.company.dto.PutResponse;
import com.company.factory.MultiLevelCacheFactory;
import com.company.multilevelcache.MultiLevelCacheImpl;
import com.company.multilevelcache.MultiLevelCacheInterface;
import com.company.strategies.EvictionPolicy;
import com.company.strategies.LRUEvictionPolicy;

public class Main {

    public static void main(String[] args) {

//        singleLevelCacheTest();

        multiLevelCacheTest();
    }

    private static void multiLevelCacheTest() {

        MultiLevelCacheInterface<Integer, Integer> multiLevelCache = MultiLevelCacheFactory.getDefaultMultiLevelCache();
        PutResponse<Integer,Integer> putResponse = null;
        putResponse = multiLevelCache.put(1,100);
        putResponse = multiLevelCache.put(2,200);
        putResponse = multiLevelCache.put(3,100);
        putResponse = multiLevelCache.put(4,200);
        putResponse = multiLevelCache.put(5,100);
        putResponse = multiLevelCache.put(6,200);

        GetResponse<Integer,Integer> getResponse = multiLevelCache.get(5);
        getResponse = multiLevelCache.get(2);

        putResponse = multiLevelCache.put(7,100);
        putResponse = multiLevelCache.put(8,200);
    }

    public static void singleLevelCacheTest() {
        int size = 3;
        EvictionPolicy<Integer> evictionPolicy = new LRUEvictionPolicy<>();
        CacheInterface<Integer,Integer> cache = new CacheImpl<>(size,evictionPolicy);

        cache.put(1,100);
        cache.put(2,101);
        cache.put(3,301);

        cache.printAllInCache();

        cache.put(4,401);
        cache.put(5,501);

        cache.printAllInCache();
    }
}
