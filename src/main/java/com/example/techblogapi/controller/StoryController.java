package com.example.techblogapi.controller;


import com.example.techblogapi.entity.Storys;
import com.example.techblogapi.security.IAuthenticationFacade;
import com.example.techblogapi.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    @GetMapping("/")
    public ResponseEntity<Iterable<Storys>> getAllStory() {

        return ResponseEntity.status(HttpStatus.OK).body(storyService.getAllStory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleStory(@PathVariable int id) {

        Storys newStory =storyService.getSingleStory(id);
        return ResponseEntity.status(HttpStatus.OK).body(newStory);
    }

    @PostMapping("/")
    public ResponseEntity<?> postStory(@RequestBody Storys storys) {

        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<Storys> newStory =storyService.postStory(storys,authentication);
        if(newStory.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not valid for "+authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(newStory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStory(@PathVariable int id, @RequestBody Storys storys) {

        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<Storys> newStory =storyService.updateStory(id,storys,authentication);
        if(newStory.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized User "+authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(newStory);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable int id) {

        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<Storys> newStory =storyService.deleteStory(id,authentication);
        if(newStory.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized User "+authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(newStory);
    }
}
