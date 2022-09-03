package com.example.techblogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex){

        ErrorBody errorBody = new ErrorBody();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errorBody.message.add(violation.getPropertyPath() + " : " + violation.getMessage());
        }
        errorBody.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorBody,HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<Object> handleSQLIntegrityConstraintViolationException (SQLIntegrityConstraintViolationException ex) {
        ErrorBody errorBody = new ErrorBody();
        errorBody.message.add(ex.getMessage());
        errorBody.setStatus(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(errorBody,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException (EntityNotFoundException ex) {
        ErrorBody errorBody = new ErrorBody();
        errorBody.message.add(ex.getMessage());
        errorBody.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorBody,HttpStatus.NOT_FOUND);
    }


}