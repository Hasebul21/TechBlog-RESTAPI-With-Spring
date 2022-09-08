package com.example.techblogapi.controller;

import com.example.techblogapi.entity.Users;
import com.example.techblogapi.security.Authenticate;
import com.example.techblogapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private Authenticate authenticate;

    @PostMapping("/signup")
    public ResponseEntity<? extends Object> signUp(@RequestBody Users users)  {
        Users newUsers =authService.signUp(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticate.authenticate(newUsers));
    }

    @PostMapping("/signin")
    public ResponseEntity<? extends Object> signIn(@RequestBody Users users) {
        Optional<Users> newUser = authService.signIn(users);
        return newUser
                .map(value -> ResponseEntity.status(HttpStatus.OK)
                        .body(authenticate.authenticate(value)))
                .orElseGet(() -> new ResponseEntity<>("Password didn't match", HttpStatus.BAD_REQUEST));
    }
}