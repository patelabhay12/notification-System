package com.notification.api.utils;

import com.notification.api.models.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> success(
            T data,
            String message) {

        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .timestamp(System.currentTimeMillis())
                        .build()
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(
            HttpStatus status,
            T data,
            String message) {

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.<T>builder()
                                .success(true)
                                .message(message)
                                .data(data)
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }

    public static ResponseEntity<ApiResponse<Object>> error(
            HttpStatus status,
            String message,
            Object errors) {

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .message(message)
                                .errors(errors)
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }
}