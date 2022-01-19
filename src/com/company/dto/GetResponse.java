package com.company.dto;

public class GetResponse<Key,Value> {

    private boolean isSuccess;
    private Key key;
    private Value value;

    public GetResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public GetResponse() {
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
