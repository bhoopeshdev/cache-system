package com.company.multilevelcache;

import com.company.cache.CacheInterface;
import com.company.dto.GetResponse;
import com.company.dto.PutResponse;

import java.util.List;

public class MultiLevelCacheImpl<Key, Value> implements MultiLevelCacheInterface<Key, Value> {

    private final List<CacheInterface<Key,Value>> cacheList;

    public MultiLevelCacheImpl(List<CacheInterface<Key, Value>> cacheList) {
        this.cacheList = cacheList;
    }

    @Override
    public PutResponse<Key, Value> put(Key key, Value value) {
       return putUtil(key,value,0);
    }

    private PutResponse<Key, Value> putUtil(Key key,Value value,int curCacheLevel)  {

        if(curCacheLevel >= cacheList.size()) {
            PutResponse<Key,Value> putResponse = new PutResponse<>();
            putResponse.setSuccess(Boolean.TRUE);
            return putResponse;
        }

        GetResponse<Key,Value> getResponse = cacheList.get(curCacheLevel).get(key);
        if(!getResponse.isSuccess()) {
            cacheList.get(curCacheLevel).put(key, value);
            System.out.println("key : " + key + " value : " + value + " putted at level " + curCacheLevel);
        }
        return putUtil(key, value, curCacheLevel + 1);
    }

    @Override
    public GetResponse<Key,Value> get(Key key) {
        return getUtil(key,0);
    }

    private GetResponse<Key,Value> getUtil(Key key, int curCacheLevel) {

        if(curCacheLevel >= cacheList.size()) {
            return new GetResponse<>(Boolean.FALSE);
        }

        GetResponse<Key,Value> getResponse = cacheList.get(curCacheLevel).get(key);
        if(!getResponse.isSuccess()) {
            getResponse = getUtil(key,curCacheLevel + 1);
            if(getResponse.isSuccess()) {
                cacheList.get(curCacheLevel).put(getResponse.getKey(), getResponse.getValue());
            }
        }
        else {
            System.out.println("key : " + key + " value : " + getResponse.getValue() + " found at level " + curCacheLevel);
        }
        return getResponse;
    }

}
