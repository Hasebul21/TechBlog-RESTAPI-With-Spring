package com.example.techblogapi.service;

import com.example.techblogapi.Utils.IsValidStory;
import com.example.techblogapi.dto.StoryDto;
import com.example.techblogapi.dto.StoryDtoConverter;
import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.AccessDeniedException;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.StoryRepository;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IsValidStory checkAuth;

    @Autowired
    private StoryDtoConverter storyDtoConverter;



    public Iterable<StoryDto> getAllStory() {

        List<Storys>allStudent=storyRepository.findAll();
        List<StoryDto>allStudentDto= allStudent.stream().map(x->storyDtoConverter.getDetails(x)).toList();
        return allStudentDto;
    }

    public StoryDto getSingleStory(int id) {

        Optional<Storys> checkStory=storyRepository.findById(id);
        if(checkStory.isEmpty()) throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        StoryDto storyDto=storyDtoConverter.getDetails(checkStory.get());
        return storyDto;

    }

    public StoryDto postStory(Storys story)  {

        String userEmail= checkAuth.getAuthName();
        Optional<Users> currentUser=userRepository.findByEmail(userEmail);
        story.setAuthorid(currentUser.get());
        storyRepository.save(story);
        StoryDto storyDto=storyDtoConverter.getDetails(story);
        return storyDto;
    }

    public StoryDto updateStory(int id, Storys story) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty())  throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        if(checkAuth.isValid(newStory)){

            Storys checkStory=newStory.get();
            checkStory.setTitle(story.getTitle());
            checkStory.setDescription(story.getDescription());
            storyRepository.save(checkStory);
            StoryDto storyDto=storyDtoConverter.getDetails(checkStory);
            return storyDto;
        }
        throw new AccessDeniedException("Unauthorized user");

    }

    public void deleteStory(int id) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty()) throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));
        if(checkAuth.isValid(newStory)) {

            storyRepository.deleteById(id);
            return;
        }
        throw new AccessDeniedException("Unauthorized user");
    }
}
