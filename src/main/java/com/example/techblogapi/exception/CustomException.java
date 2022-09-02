package com.example.techblogapi.exception;

import org.springframework.http.HttpStatus;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;

public class CustomException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public CustomException(String message, HttpStatus status){

        this.message=message;
        this.status=status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
