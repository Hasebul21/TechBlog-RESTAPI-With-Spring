package com.example.techblogapi.service;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getSingleUser(int id) {

        Optional<User> checkUser=userRepository.findById(id);
        if(checkUser.isPresent()) return checkUser;
        return Optional.empty();

    }

    public Optional<User> updateUser(int id,User user) {

        Optional<User> newUser=userRepository.findByEmail(id);
        if(user.getEmail().isEmpty()||user.getPassword().isEmpty()) return Optional.empty();
        if(userRepository.findByEmail(user.getEmail()).isPresent()) return Optional.empty();
        if(newUser.isPresent()){

            newUser.get().setEmail(user.getEmail());
            newUser.get().setPassword(user.getPassword());
            newUser.get().setName(user.getName());
            newUser.get().setPhone(user.getPhone());
            userRepository.save(newUser.get());
            return newUser;
        }
        return Optional.empty();
    }

    public Optional<User> deleteUser(int id) {
        Optional<User> newUser=userRepository.findById(id);
        if(newUser.isEmpty()) return Optional.empty();
        userRepository.deleteById(id);
        return newUser;

    }
}