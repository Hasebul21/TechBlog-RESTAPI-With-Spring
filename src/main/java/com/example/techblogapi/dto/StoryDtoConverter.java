package com.example.techblogapi.dto;

import com.example.techblogapi.entity.Storys;
import org.springframework.stereotype.Component;

@Component
public class StoryDtoConverter {

    public StoryDto getDetails(Storys story){

        StoryDto storyDto=new StoryDto();
        storyDto.setId(story.getId());
        storyDto.setTitle(story.getTitle());
        storyDto.setDescription(story.getDescription());
        storyDto.setAuthor(story.getAuthorid().getEmail());
        storyDto.setCreatedDate(story.getCreatedDate());
        return storyDto;
    }
}
