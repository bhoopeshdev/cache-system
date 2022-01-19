package com.company.multilevelcache;

import com.company.dto.GetResponse;
import com.company.dto.PutResponse;

public interface MultiLevelCacheInterface<Key,Value> {

    PutResponse<Key,Value> put(Key key, Value value);

    GetResponse<Key,Value> get(Key key);
}
