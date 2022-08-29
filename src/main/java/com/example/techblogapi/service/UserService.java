package com.example.techblogapi.service;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user)  {

        if(userRepository.existsByEmail(user.getEmail())==false) {

            try {

                userRepository.save(user);

            }catch(Exception er){

                System.out.println(er.getMessage().toString());
            }

        }else System.out.println("Already Exist");
    }

    public Iterable<User> getUser() {

        return userRepository.findAll();
    }

    public Optional<User> getSingleUser(int id) {

        return userRepository.findById(id);
    }

    public void updateUser(String email,User user) {

        if(userRepository.existsByEmail(email)){

           User user1=userRepository.findByEmail(email);
           user1.setEmail(user.getEmail());
           user1.setPassword(user.getPassword());
           userRepository.save(user1);
        }
        else System.out.println("NOT FOUND");
    }

    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }
}
