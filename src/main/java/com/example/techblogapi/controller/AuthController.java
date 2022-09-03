package com.example.techblogapi.controller;

import com.example.techblogapi.entity.User;
import com.example.techblogapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<? extends Object> signUp(@RequestBody User user)  {

        User newUser=authService.signUp(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<? extends Object> signIn(@RequestBody User user) {

        User newUser=authService.signIn(user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);

    }
}