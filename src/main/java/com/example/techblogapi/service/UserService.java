package com.example.techblogapi.service;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
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

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        if(checkUser.isEmpty()) return Optional.empty();
        return checkUser;

    }

    public Optional<User> updateUser(int id,User user)  {

        Optional<User> newUser=userRepository.findById(id);
        //if(user.getEmail().isBlank()||user.getPassword().isBlank()||user.getName().isBlank()||user.getPhone().isBlank()) throw new NumberFormatException("Input Field is empty") ;
        if(newUser.isPresent()){

            newUser.get().setEmail(user.getEmail());
            newUser.get().setPassword(user.getPassword());
            newUser.get().setName(user.getName());
            newUser.get().setPhone(user.getPhone());
            userRepository.save(newUser.get());
        }
        return newUser;
    }

    public Optional<User> deleteUser(int id) {

        Optional<User> newUser=userRepository.findById(id);
        if(newUser.isEmpty()) return Optional.empty();
        userRepository.deleteById(id);
        return newUser;

    }
}