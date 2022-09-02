package com.example.techblogapi.controller;

import com.example.techblogapi.entity.User;
import com.example.techblogapi.exception.CustomException;
import com.example.techblogapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<? extends Object> signUp(@RequestBody User user)  {

           //SQLIntegrityConstraintViolationException
           //ConstraintViolationException
            User newUser=authService.signUp(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<? extends Object> signIn(@RequestBody User user) {

        Optional<User>newUser=authService.signIn(user);
        if(newUser.isEmpty()) throw new CustomException("User Not Found",HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(newUser.get());

    }
}