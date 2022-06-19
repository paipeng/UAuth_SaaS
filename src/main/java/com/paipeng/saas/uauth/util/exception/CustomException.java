package com.paipeng.saas.uauth.util.exception;

public class CustomException extends RuntimeException {
    private Object object;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public Object getObject() {
        return object;
    }

    public CustomException setObject(Object object) {
        this.object = object;
        return this;
    }
}
