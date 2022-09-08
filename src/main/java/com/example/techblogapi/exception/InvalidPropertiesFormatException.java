package com.example.techblogapi.exception;

public class InvalidPropertiesFormatException extends RuntimeException{

    public InvalidPropertiesFormatException(String key) {

        super("Invalid Password "+key);
    }
}
