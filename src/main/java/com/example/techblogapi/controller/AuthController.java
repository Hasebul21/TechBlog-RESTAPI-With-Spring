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

    @PostMapping(path="${v1API}/signup")
    public ResponseEntity<?> signUp(@RequestBody Users users)  {
        Users newUser =authService.signUp(users);
        String token=authenticate.authenticate(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @PostMapping(path="${v1API}/signin")
    public ResponseEntity<?> signIn(@RequestBody Users users) {
        Optional<Users> newUser = authService.signIn(users);
        if(newUser.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(users.getEmail()+" and "+users.getPassword()+" didnot match");
        String token=authenticate.authenticate(newUser.get());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}