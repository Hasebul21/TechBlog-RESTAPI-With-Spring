package com.example.techblogapi.security;

import com.example.techblogapi.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class Authenticate {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsInfo userDetailsInfo;

    public String authenticate(Users users){


        final UserDetails userDetails=userDetailsInfo.loadUserByUsername(users.getEmail());
        final String token= jwtUtil.generateToken(userDetails);
        return token;

    }
}
