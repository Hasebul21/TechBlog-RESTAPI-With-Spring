package com.example.techblogapi.service;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> addUser(User user)  {

        try {

            userRepository.save(user);

        }catch(Exception err){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage().toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Sucessfully added entity");

    }

    public ResponseEntity<List<User>> getAllUser() {

        List<User>allUser=new ArrayList<>();
        userRepository.findAll().forEach(allUser::add);
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
    }

    public ResponseEntity<User> getSingleUser(int id) {

        Optional<User> checkUser=userRepository.findById(id);
        if(checkUser.isPresent()==false) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User existingUser=checkUser.get();
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);

    }

    public ResponseEntity<String> updateUser(String email,User user) {

        if(userRepository.existsByEmail(email)){

            Optional<User> checkUser=userRepository.findByEmail(email);
            User newUser=checkUser.get();
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body("Sucessfully Updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Doesnot Exist");
    }

    public ResponseEntity<String> deleteUser(int id) {

        try {

            userRepository.deleteById(id);

        }catch(Exception err){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage().toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Sucessfully Deleted entity");
    }
}