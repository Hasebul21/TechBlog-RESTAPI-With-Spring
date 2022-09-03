package com.example.techblogapi.service;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUser() {

        return userRepository.findAll();
    }

    public User getSingleUser(int id) {

        Optional<User> checkUser=userRepository.findById(id);
        if(checkUser.isPresent()) return checkUser.get();
        throw new EntityNotFoundException("User with id "+id+" does not exist");

    }

    public User updateUser(int id,User user)  {

        Optional<User> newUser=userRepository.findById(id);
        if(newUser.isEmpty()) throw new EntityNotFoundException("User with id "+id+" does not exist");

        newUser.get().setEmail(user.getEmail());
        newUser.get().setPassword(user.getPassword());
        newUser.get().setName(user.getName());
        newUser.get().setPhone(user.getPhone());
        userRepository.save(newUser.get());
        return newUser.get();
    }

    public User deleteUser(int id) {

        Optional<User> newUser=userRepository.findById(id);
        if(newUser.isPresent()) {

            userRepository.deleteById(id);
            return newUser.get();
        }
        throw new EntityNotFoundException("User with id "+id+" does not exist");
    }
}