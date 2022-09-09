package com.example.techblogapi.service;

import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.StoryRepository;
import com.example.techblogapi.repository.UserRepository;
import com.example.techblogapi.security.JwtUtil;
import com.example.techblogapi.security.UserDetailsInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Storys> postStory(Storys storys, String token)  {


        if(jwtUtil.isTokenExpired(token)) return Optional.empty();
        String userEmail= jwtUtil.extractUsername(token);
        Optional<Users> currentUser=userRepository.findByEmail(userEmail);
        storys.setAuthorid(currentUser.get());
        storyRepository.save(storys);
        return Optional.of(storys);

    }

    public Storys updateStory(int id, Storys storys,String token) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty())  throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
       // String userEmail= jwtUtil.extractUsername(token);
        //UserDetails userDetails=userDetailsInfo.loadUserByUsername(userEmail);
        //if(!jwtUtil.validateToken(token,userDetails))

        newStory.get().setTitle(storys.getTitle());
        newStory.get().setDescription(storys.getTitle());

        return storyRepository.save(newStory.get());
    }

    public Storys deleteStory(int id) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isPresent()) {
            storyRepository.deleteById(id);
            return newStory.get();
        }
        throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
    }
}
