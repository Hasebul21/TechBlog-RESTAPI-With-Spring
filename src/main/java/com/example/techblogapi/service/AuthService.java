package com.example.techblogapi.service;

import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    public Users signUp(Users users)  {

        return userRepository.save(users);

    }

    public Optional<Users> signIn(Users users)   {

        String userEmail= users.getEmail();
        String userPassword= users.getPassword();
        Optional<Users> newUser=userRepository.findByEmail(userEmail);
        if(newUser.isEmpty()) throw new EntityNotFoundException(Users.class,"Email",userEmail);
        if(newUser.get().getPassword().equals(userPassword)) return newUser;
        return Optional.empty();
    }
}