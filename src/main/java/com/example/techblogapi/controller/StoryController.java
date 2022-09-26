package com.example.techblogapi.controller;


import com.example.techblogapi.dto.StoryDto;
import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${v1API}/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;


    @GetMapping("/")
    public ResponseEntity<List<StoryDto>> getAllStory() {
        return ResponseEntity.status(HttpStatus.OK).body(storyService.getAllStory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleStory(@PathVariable int id) {

        StoryDto newStory =storyService.getSingleStory(id);
        return ResponseEntity.status(HttpStatus.OK).body(newStory);
    }

    @PostMapping(value="/" , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<?> postStory(@RequestBody Storys storys) {

        StoryDto newStory =storyService.postStory(storys);
        //return ResponseEntity.status(HttpStatus.CREATED).body(newStory);
        return new ResponseEntity<>(newStory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStory(@PathVariable int id, @RequestBody Storys storys) {

        StoryDto newStory =storyService.updateStory(id,storys);
        return ResponseEntity.status(HttpStatus.OK).body(newStory);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable int id) {

        storyService.deleteStory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
