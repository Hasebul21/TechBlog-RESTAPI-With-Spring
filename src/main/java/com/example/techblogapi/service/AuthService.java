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
    public Users signUp(Users user)  {

        if(passwordValidator.isValid(user.getPassword())) {

            String pass=passwordEncoder.encode(user.getPassword());
            user.setPassword(pass);
            return userRepository.save(user);

        }
        throw new InvalidPasswordException(user.getPassword());

    }

    public Optional<Users> signIn(Users user)   {

        String userEmail= user.getEmail();
        String userPassword= user.getPassword();
        Optional<Users> newUser=userRepository.findByEmail(userEmail);
        if(newUser.isEmpty()) throw new EntityNotFoundException(Users.class,"Email",userEmail);
        String hashPass=newUser.get().getPassword();
        if(!passwordEncoder.matches(userPassword, hashPass)) return Optional.empty();
        return newUser;
    }
}