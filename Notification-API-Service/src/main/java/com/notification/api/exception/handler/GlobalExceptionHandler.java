package com.notification.api.exception.handler;

import com.notification.api.exception.AbstractException;
import com.notification.api.exception.ResourceNotFoundException;
import com.notification.api.exception.ValidationException;
import com.notification.api.utils.CommonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.function.Supplier;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validationExceptionHandler(ValidationException exception){
        return genericExceptionHandler(exception,()->{
            return ResponseEntity.badRequest().body(exception.getMessage());
        });
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException exception){
       return genericExceptionHandler(exception,()->{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
       });
    }


    public ResponseEntity<String> genericExceptionHandler(final AbstractException exception,final Supplier<ResponseEntity<String>> runner){

        if(CommonUtils.isNotEmpty(exception.getStatusCode())){
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getErrorMessage());
        }
        return runner.get();
    }
}
