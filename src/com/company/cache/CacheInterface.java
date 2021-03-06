package com.company.cache;

import com.company.dto.GetResponse;
import com.company.dto.PutResponse;

public interface CacheInterface<Key,Value> {

    PutResponse<Key,Value> put(Key key, Value value);

    GetResponse<Key,Value> get(Key key);

    void printAllInCache();

}
