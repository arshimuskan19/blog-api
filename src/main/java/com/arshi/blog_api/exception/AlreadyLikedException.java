package com.arshi.blog_api.exception;

public class AlreadyLikedException extends RuntimeException {
    public AlreadyLikedException(String message) {
        super(message);
    }
}
