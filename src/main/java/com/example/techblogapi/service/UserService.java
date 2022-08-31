package com.example.techblogapi.service;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUser()  {

        return userRepository.findAll();
    }

    public Optional<User> getSingleUser(int id) {

        Optional<User> checkUser=userRepository.findById(id);
        if(checkUser.isPresent()) return checkUser;
        return Optional.empty();

    }

    public Optional<User> updateUser(int id,User user) {

        Optional<User> newUser=userRepository.findById(id);
        if(user.getEmail().isEmpty()||user.getPassword().isEmpty()||user.getName().isEmpty()||user.getPhone().isEmpty()) return Optional.empty();

        if(newUser.isPresent()){

            newUser.get().setEmail(user.getEmail());
            newUser.get().setPassword(user.getPassword());
            newUser.get().setName(user.getName());
            newUser.get().setPhone(user.getPhone());
            userRepository.save(newUser.get());
            return newUser;
        }
        return Optional.empty();
    }

    public Optional<User> deleteUser(int id) {

        Optional<User> newUser=userRepository.findById(id);
        if(newUser.isEmpty()) return Optional.empty();
        userRepository.deleteById(id);
        return newUser;

    }
}