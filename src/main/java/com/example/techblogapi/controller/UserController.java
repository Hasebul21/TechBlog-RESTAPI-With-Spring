package com.example.techblogapi.controller;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

          User newUser=userService.getSingleUser(id);
          return ResponseEntity.status(HttpStatus.OK).body(newUser);
     }


     @PutMapping("/{id}")
     public ResponseEntity<? extends Object> updateUser(@PathVariable int id,@RequestBody User user) {


        User newUser=userService.updateUser(id,user);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);

     }

     @DeleteMapping("/{id}")
     public ResponseEntity<? extends Object> deleteUser(@PathVariable int id) {

         User newUser=userService.deleteUser(id);
         return ResponseEntity.status(HttpStatus.OK).body(newUser);
     }

}