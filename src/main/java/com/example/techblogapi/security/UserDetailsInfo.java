package com.example.techblogapi.security;

import com.example.techblogapi.repository.UserRepository;
import com.example.techblogapi.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsInfo implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Users> newUser=userRepository.findByEmail(email);
        Users realUsers =newUser.get();
        return new User(realUsers.getEmail(), realUsers.getPassword(),new ArrayList<>());

    }
}
