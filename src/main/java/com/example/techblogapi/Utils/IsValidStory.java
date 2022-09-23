package com.example.techblogapi.Utils;

import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.AccessDeniedException;
import com.example.techblogapi.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IsValidStory {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public boolean isValid(Optional<Storys> newStory){

        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.isAuthenticated()) return false;
        String CurrentUserEmail= authentication.getName();
        Users authorDetails=newStory.get().getAuthorid();
        String authEmail=authorDetails.getEmail();
        return CurrentUserEmail.equals(authEmail);
    }

    public String getAuthName(){
        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.isAuthenticated())  throw new AccessDeniedException(authentication.getName()+" is not Authenticated");
        return authentication.getName();
    }
}
