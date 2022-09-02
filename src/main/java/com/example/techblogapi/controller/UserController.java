package com.example.techblogapi.controller;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.exception.CustomException;
import com.example.techblogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

     @Autowired
     private UserService userService;


    @GetMapping("/")
     public ResponseEntity<Iterable<User>> getAllUser() {

          return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
     }

     @GetMapping("/{id}")
     public ResponseEntity<? extends Object> getSingleUser(@PathVariable int id) {

          Optional<User> newUser=userService.getSingleUser(id);
          if(newUser.isEmpty()) throw new CustomException("User Not Found",HttpStatus.NOT_FOUND);
          return ResponseEntity.status(HttpStatus.OK).body(newUser.get());
     }


     @PutMapping("/{id}")
     public ResponseEntity<? extends Object> updateUser(@PathVariable int id,@RequestBody User user) throws ConstraintViolationException {


        Optional<User> newUser=userService.updateUser(id,user);
        if(newUser.isEmpty()) throw new EntityNotFoundException("User Does not Exist");
        return ResponseEntity.status(HttpStatus.OK).body(newUser.get());

     }

     @DeleteMapping("/{id}")
     public ResponseEntity<? extends Object> deleteUser(@PathVariable int id) {

         Optional<User> newUser=userService.deleteUser(id);
         if(newUser.isEmpty()) throw new EntityNotFoundException("User Does not Exist");
         return ResponseEntity.status(HttpStatus.OK).body(newUser.get());
     }

}