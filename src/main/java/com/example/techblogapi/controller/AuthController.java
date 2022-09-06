package com.example.techblogapi.controller;

import com.example.techblogapi.entity.Users;
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


    @PostMapping("/signup")
    public ResponseEntity<? extends Object> signUp(@RequestBody Users users)  {

        Users newUsers =authService.signUp(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsers);
    }

    @PostMapping("/signin")
    public ResponseEntity<? extends Object> signIn(@RequestBody Users users) {

        Optional<Users> newUser =authService.signIn(users);
        if (newUser.isPresent()) return ResponseEntity.status(HttpStatus.OK).body(newUser);
        return new ResponseEntity<>("Password didn't match", HttpStatus.BAD_REQUEST);
    }
}