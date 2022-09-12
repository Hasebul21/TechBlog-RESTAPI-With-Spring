package com.example.techblogapi.service;

import com.example.techblogapi.Utils.PasswordValidator;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.exception.InvalidPasswordException;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private PasswordValidator passwordValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public Users signUp(Users users)  {

        if(passwordValidator.isValid(users.getPassword())) {

            String pass=passwordEncoder.encode(users.getPassword());
            users.setPassword(pass);
            return userRepository.save(users);

        }
        throw new InvalidPasswordException(users.getPassword());

    }

    public Optional<Users> signIn(Users users)   {

        String userEmail= users.getEmail();
        String userPassword= users.getPassword();
        Optional<Users> newUser=userRepository.findByEmail(userEmail);
        if(newUser.isEmpty()) throw new EntityNotFoundException(Users.class,"Email",userEmail);
        String hashPass=newUser.get().getPassword();
        if(!passwordEncoder.matches(userPassword, hashPass)) return Optional.empty();
        return newUser;
    }
}