package com.example.techblogapi.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String key) {

        super("Invalid Password "+key);
    }
}
