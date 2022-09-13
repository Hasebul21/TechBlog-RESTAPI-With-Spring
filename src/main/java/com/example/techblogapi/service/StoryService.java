package com.example.techblogapi.service;

import com.example.techblogapi.Dto.StoryDto;
import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.AccessDeniedException;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.StoryRepository;
import com.example.techblogapi.repository.UserRepository;
import com.example.techblogapi.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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


    public Iterable<Storys> getAllStory() {

        return storyRepository.findAll();
    }

    public StoryDto getSingleStory(int id) {

        Optional<Storys> checkStory=storyRepository.findById(id);
        if(checkStory.isEmpty()) throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        StoryDto storyDto=getDetails(checkStory.get());
        return storyDto;

    }

    public StoryDto postStory(Storys story)  {

        Authentication authentication = authenticationFacade.getAuthentication();
        if(!authentication.isAuthenticated())  throw new AccessDeniedException(authentication.getName()+" is not Authenticated");
        String userEmail= authentication.getName();
        Optional<Users> currentUser=userRepository.findByEmail(userEmail);
        story.setAuthorid(currentUser.get());
        storyRepository.save(story);
        StoryDto storyDto=getDetails(story);
        return storyDto;
    }

    public StoryDto updateStory(int id, Storys story) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty())  throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        if(checkAuth(newStory)){

            Storys checkStory=newStory.get();
            checkStory.setTitle(story.getTitle());
            checkStory.setDescription(story.getDescription());
            storyRepository.save(checkStory);
            StoryDto storyDto=getDetails(checkStory);
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

    public StoryDto getDetails(Storys story){

        StoryDto storyDto1=new StoryDto();
        storyDto1.setId(story.getId());
        storyDto1.setTitle(story.getTitle());
        storyDto1.setDescription(story.getDescription());
        storyDto1.setAuthor(story.getAuthorid().getEmail());
        storyDto1.setCreatedDate(story.getCreatedDate());
        return storyDto1;
    }
}
