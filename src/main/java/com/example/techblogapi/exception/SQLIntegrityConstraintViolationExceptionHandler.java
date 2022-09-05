package com.example.techblogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLIntegrityConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler implements RestExceptionHandler<SQLIntegrityConstraintViolationException>{


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handle(SQLIntegrityConstraintViolationException ex) {

        List<String> Error=new ArrayList<>();
        Error.add(ex.getMessage());
        ErrorBody errorBody = new ErrorBody();
        errorBody.setMessage(Error);
        errorBody.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorBody,HttpStatus.BAD_REQUEST);
    }
}
