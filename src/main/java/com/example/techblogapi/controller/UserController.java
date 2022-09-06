package com.example.techblogapi.controller;


import com.example.techblogapi.entity.Users;
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
     public ResponseEntity<Iterable<Users>> getAllUser() {

          return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
     }

     @GetMapping("/{id}")
     public ResponseEntity<? extends Object> getSingleUser(@PathVariable int id) {

          Users newUsers =userService.getSingleUser(id);
          return ResponseEntity.status(HttpStatus.OK).body(newUsers);
     }


     @PutMapping("/{id}")
     public ResponseEntity<? extends Object> updateUser(@PathVariable int id,@RequestBody Users users) {


        Users newUsers =userService.updateUser(id, users);
        return ResponseEntity.status(HttpStatus.OK).body(newUsers);

     }

     @DeleteMapping("/{id}")
     public ResponseEntity<? extends Object> deleteUser(@PathVariable int id) {

         Users newUsers =userService.deleteUser(id);
         return ResponseEntity.status(HttpStatus.OK).body(newUsers);
     }

}