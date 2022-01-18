package com.company;

import com.company.cache.CacheImpl;
import com.company.cache.CacheInterface;
import com.company.strategies.EvictionPolicy;
import com.company.strategies.LRUEvictionPolicy;

public class Main {

    public static void main(String[] args) {

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
