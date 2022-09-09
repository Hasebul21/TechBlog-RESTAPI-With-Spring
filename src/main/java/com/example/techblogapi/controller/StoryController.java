package com.example.techblogapi.controller;


import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/storys")
public class StoryController {

    @Autowired
    private StoryService storyService;


    @GetMapping("/")
    public ResponseEntity<Iterable<Storys>> getAllStory() {

        return ResponseEntity.status(HttpStatus.OK).body(storyService.getAllStory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getSingleStory(@PathVariable int id) {

        Storys newStory =storyService.getSingleStory(id);
        return ResponseEntity.status(HttpStatus.OK).body(newStory);
    }

    @PostMapping("/")
    public ResponseEntity<? extends Object> postStory(@RequestBody Storys storys,@RequestHeader("Authorization") String token,
                                                      Authentication authentication) {
        System.out.println(authentication.isAuthenticated());
        String gettoken= token.substring(7);
        Optional<Storys> newStory =storyService.postStory(storys,gettoken);
        if(newStory.isPresent()) return ResponseEntity.status(HttpStatus.OK).body(newStory);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not valid");
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateStory(@PathVariable int id, @RequestBody Storys storys,
                                                        @RequestHeader("Authorization") String token) {
        String gettoken= token.substring(7);
        Storys newStory =storyService.updateStory(id, storys,token);
        return ResponseEntity.status(HttpStatus.OK).body(newStory);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteStory(@PathVariable int id) {

        Storys newStory =storyService.deleteStory(id);
        return ResponseEntity.status(HttpStatus.OK).body(newStory);
    }
}
