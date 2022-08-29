package com.example.techblogapi.controller;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import com.example.techblogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

     @Autowired
     private UserService userService;


     @RequestMapping(method = RequestMethod.GET, value = "/users")
     public Iterable<User> getUser() {

          return userService.getUser();
     }

     @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
     public Optional<User> getSingleUser(@PathVariable int id) {

          return userService.getSingleUser(id);
     }

     @RequestMapping(method = RequestMethod.POST,value = "/users")
     public void addUser(@RequestBody User user)  {

          System.out.println(user.getEmail());
          userService.addUser(user);
     }

     @RequestMapping(method = RequestMethod.PUT,value = "/users/{email}")
     public void updateUser(@PathVariable String email,@RequestBody User user) {

          userService.updateUser(email,user);
     }

     @RequestMapping(method = RequestMethod.DELETE,value = "/users")
     public void updateUser(@RequestBody User user) {

          userService.deleteUser(user.getId());
     }

}
