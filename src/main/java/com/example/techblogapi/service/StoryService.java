package com.example.techblogapi.service;

import com.example.techblogapi.Utils.StoryDto;
import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.AccessDeniedException;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.StoryRepository;
import com.example.techblogapi.repository.UserRepository;
import com.example.techblogapi.security.IAuthenticationFacade;
import com.example.techblogapi.security.JwtUtil;
import com.example.techblogapi.security.UserDetailsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private StoryDto storyDto;

    public Iterable<Storys> getAllStory() {

        return storyRepository.findAll();
    }

    public StoryDto getSingleStory(int id) {

        Optional<Storys> checkStory=storyRepository.findById(id);
        if(checkStory.isEmpty()) throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        storyDto=getDetails(checkStory.get());
        return storyDto;

    }

    public StoryDto postStory(Storys storys)  {

        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.isAuthenticated())  throw new AccessDeniedException(authentication.getName()+" is not Authenticated");
        String userEmail= authentication.getName();
        Optional<Users> currentUser=userRepository.findByEmail(userEmail);
        storys.setAuthorid(currentUser.get());
        storyRepository.save(storys);
        storyDto=getDetails(storys);
        return storyDto;
    }

    public StoryDto updateStory(int id, Storys storys) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty())  throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        if(checkAuth(newStory)){

            newStory.get().setTitle(storys.getTitle());
            newStory.get().setDescription(storys.getDescription());
            storyRepository.save(newStory.get());
            storyDto=getDetails(newStory.get());
            return storyDto;
        }
        throw new AccessDeniedException(authenticationFacade.getAuthentication().getName()+" is an Unauthorized user");

    }

    public void deleteStory(int id) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty()) throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        if(checkAuth(newStory)) {

            storyRepository.deleteById(id);
        }
        throw new AccessDeniedException(authenticationFacade.getAuthentication().getName()+" is an Unauthorized user");
    }

    public boolean checkAuth(Optional<Storys> newStory){

        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.isAuthenticated()) return false;
        String CurrentUserEmail= authentication.getName();
        Users authorDetails=newStory.get().getAuthorid();
        String authEmail=authorDetails.getEmail();
        return CurrentUserEmail.equals(authEmail);
    }

    public StoryDto getDetails(Storys storys){

        StoryDto storyDto1=new StoryDto();
        storyDto1.setId(storys.getId());
        storyDto1.setTitle(storys.getTitle());
        storyDto1.setDescription(storys.getDescription());
        storyDto1.setAuthor(storys.getAuthorid().getEmail());
        storyDto1.setCreatedDate(storys.getCreatedDate());
        return storyDto1;
    }
}
