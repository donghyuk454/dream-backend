package com.dream.application.common.exception;

public class DreamException extends RuntimeException {

    public DreamException(String key) {
        super(getMessage(key));
    }

    private static String getMessage(String key) {
        return key;
    }
}
