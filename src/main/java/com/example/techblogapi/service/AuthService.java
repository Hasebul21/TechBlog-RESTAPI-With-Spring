package com.example.techblogapi.service;

import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    public User signUp(User user)   {

        return userRepository.save(user);
    }

    public Optional<User> signIn(User user)   {

        String userEmail=user.getEmail();
        String userPassword=user.getPassword();
        Optional<User> newUser=userRepository.findByEmail(userEmail);
        if(newUser.isPresent()&&newUser.get().getPassword().equals(userPassword)) return newUser;
        return Optional.empty();

    }
}