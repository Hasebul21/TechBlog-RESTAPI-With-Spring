package com.example.techblogapi.controller;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import com.example.techblogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

     @Autowired
     private UserService userService;


     @RequestMapping(method = RequestMethod.GET, value = "/users")
     public ResponseEntity<List<User>> getAllUser() {

          return userService.getAllUser();
     }

     @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
     public ResponseEntity<User> getSingleUser(@PathVariable int id) {

          return userService.getSingleUser(id);
     }

     @RequestMapping(method = RequestMethod.POST,value = "/users")
     public ResponseEntity<String> addUser(@RequestBody User user)  {

          return userService.addUser(user);
     }

     @RequestMapping(method = RequestMethod.PUT,value = "/users/{email}")
     public ResponseEntity<String> updateUser(@PathVariable String email,@RequestBody User user) {

          return userService.updateUser(email,user);
     }

     @RequestMapping(method = RequestMethod.DELETE,value = "/users")
     public ResponseEntity<String> deleteUser(@RequestBody User user) {

          return userService.deleteUser(user.getId());
     }

}