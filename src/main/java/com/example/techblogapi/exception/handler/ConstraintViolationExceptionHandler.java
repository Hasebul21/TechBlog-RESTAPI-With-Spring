package com.example.techblogapi.exception.handler;

import com.example.techblogapi.exception.ErrorBody;
import com.example.techblogapi.exception.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler implements RestExceptionHandler<ConstraintViolationException> {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handle(ConstraintViolationException ex) {

        List<String>Error=new ArrayList<>();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
           Error.add(violation.getPropertyPath() + " : " + violation.getMessage());
        }
        ErrorBody errorBody = new ErrorBody();
        errorBody.setMessage(Error);
        errorBody.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorBody,HttpStatus.BAD_REQUEST);
    }
}
