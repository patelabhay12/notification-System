package com.notification.api.exception;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException implements AbstractException{

    Integer statusCode;

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message,Integer statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getErrorMessage() {
        return getMessage();
    }
}
