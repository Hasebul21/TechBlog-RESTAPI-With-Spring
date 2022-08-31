package com.example.techblogapi.controller;

import com.example.techblogapi.entity.User;
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
    public ResponseEntity<User> signUp(@RequestBody User user){

        try {

            User newUser=authService.signUp(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@RequestBody User user){

        Optional<User>newUser=authService.signIn(user);
        if(newUser.isPresent()) return ResponseEntity.status(HttpStatus.OK).body(newUser.get());
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}