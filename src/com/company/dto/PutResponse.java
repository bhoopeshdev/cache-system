package com.company.dto;

public class PutResponse<Key,Value> {

    boolean isSuccess;

    Key evictedKey;

    Value evictedValue;

    public PutResponse() {
    }

    public PutResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Key getEvictedKey() {
        return evictedKey;
    }

    public void setEvictedKey(Key evictedKey) {
        this.evictedKey = evictedKey;
    }

    public Value getEvictedValue() {
        return evictedValue;
    }

    public void setEvictedValue(Value evictedValue) {
        this.evictedValue = evictedValue;
    }
}
