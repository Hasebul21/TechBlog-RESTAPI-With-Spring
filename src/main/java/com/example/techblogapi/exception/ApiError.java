package com.example.techblogapi.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

class ApiError {

    private HttpStatus status;

    private LocalDateTime timestamp;
    private String message;


    public ApiError() {

        timestamp = LocalDateTime.now();
    }

   /* public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }*/

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
