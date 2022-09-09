package com.example.techblogapi.service;

import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.StoryRepository;
import com.example.techblogapi.repository.UserRepository;
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
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsInfo userDetailsInfo;


    public Iterable<Storys> getAllStory() {

        return storyRepository.findAll();
    }

    public Storys getSingleStory(int id) {

        Optional<Storys> checkStory=storyRepository.findById(id);
        if(checkStory.isPresent()) return checkStory.get();
        throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));

    }

    public Optional<Storys> postStory(Storys storys, Authentication authentication)  {


        if(!authentication.isAuthenticated()) return Optional.empty();
        String userEmail= authentication.getName();
        Optional<Users> currentUser=userRepository.findByEmail(userEmail);
        storys.setAuthorid(currentUser.get());
        storyRepository.save(storys);
        return Optional.of(storys);

    }

    public Optional<Storys> updateStory(int id, Storys storys, Authentication authentication) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty())  throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        if(checkAuth(newStory,authentication)){

            newStory.get().setTitle(storys.getTitle());
            newStory.get().setDescription(storys.getDescription());
            storyRepository.save(newStory.get());
        }
        return Optional.empty();
    }

    public Optional<Storys> deleteStory(int id,Authentication authentication) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty()) throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        if(checkAuth(newStory,authentication)) {

            storyRepository.deleteById(id);
        }
        return Optional.empty();
    }

    public boolean checkAuth(Optional<Storys> newStory,Authentication authentication){

        if(!authentication.isAuthenticated()) return false;
        String CurrentUserEmail= authentication.getName();
        Users authorDetails=newStory.get().getAuthorid();
        String authEmail=authorDetails.getEmail();
        return CurrentUserEmail.equals(authEmail);
    }
}
