package com.cinar.newsAPI.exception;

public class NewsNotFoundException extends RuntimeException{
    public NewsNotFoundException(String message) {
        super(message);
    }
}
