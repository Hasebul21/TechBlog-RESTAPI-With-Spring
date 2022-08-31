package com.example.techblogapi.controller;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

     @Autowired
     private UserService userService;


    @GetMapping
     public ResponseEntity<Iterable<User>> getAllUser() {

          return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
     }

     @GetMapping("/{id}")
     public ResponseEntity<User> getSingleUser(@PathVariable int id) {

          Optional<User> newUser=userService.getSingleUser(id);
          if(newUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
          return ResponseEntity.status(HttpStatus.OK).body(newUser.get());
     }

     @PutMapping("/{id}")
     public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user) {

          Optional<User> newUser=userService.updateUser(id,user);
          if(newUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
          return ResponseEntity.status(HttpStatus.ACCEPTED).body(newUser.get());
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<User> deleteUser(@PathVariable int id) {

        Optional<User> newUser=userService.deleteUser(id);
        if(newUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(newUser.get());

     }

}