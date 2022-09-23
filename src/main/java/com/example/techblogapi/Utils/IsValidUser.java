package com.example.techblogapi.Utils;

import com.example.techblogapi.entity.Users;
import com.example.techblogapi.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class IsValidUser {


    @Autowired
    private IAuthenticationFacade authenticationFacade;


    public boolean isValid(Users newUser){

        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.isAuthenticated()) return false;
        String CurrentUserEmail= authentication.getName();
        String authorEMail=newUser.getEmail();
        return CurrentUserEmail.equals(authorEMail);
    }
}
