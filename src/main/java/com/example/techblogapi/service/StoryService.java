package com.example.techblogapi.service;

import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    public Iterable<Storys> getAllStory() {

        return storyRepository.findAll();
    }

    public Storys getSingleStory(int id) {

        Optional<Storys> checkStory=storyRepository.findById(id);
        if(checkStory.isPresent()) return checkStory.get();
        throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));

    }

    public Storys postStory(Storys storys)  {

        return storyRepository.save(storys);

    }

    public Storys updateStory(int id, Storys storys) {

        Optional<Storys> newStory=storyRepository.findById(id);
        if(newStory.isEmpty())  throw new EntityNotFoundException(Storys.class,"id",String.valueOf(id));

        newStory.get().setAuthorName(storys.getAuthorName());
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
