package com.example.techblogapi.controller;


import com.example.techblogapi.dto.UserDto;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${v1API}/users")
public class UserController {

     @Autowired
     private UserService userService;


    @GetMapping("/")
     public ResponseEntity<List<UserDto>> getAllUser() {

          return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> getSingleUser(@PathVariable int id) {

          UserDto newUsers =userService.getSingleUser(id);
          return ResponseEntity.status(HttpStatus.OK).body(newUsers);
     }

     @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Users users) {

        UserDto newUsers = userService.updateUser(id, users);
        return new ResponseEntity<>(newUsers, HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteUser(@PathVariable int id) {

         userService.deleteUser(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }

}