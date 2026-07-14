package com.arshi.blog_api.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message){
        super(message);
    }
}
