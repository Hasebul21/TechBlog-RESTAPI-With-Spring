package com.example.techblogapi.service;


import com.example.techblogapi.Utils.CheckAuthorValidity;
import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.AccessDeniedException;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckAuthorValidity checkAuth;


    public List<Users> getAllUser() {

        return userRepository.findAll();
    }

    public Users getSingleUser(int id) {

        Optional<Users> checkUser=userRepository.findById(id);
        if(checkUser.isPresent()) return checkUser.get();
        throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));

    }

    public Users updateUser(int id, Users users) {

        Optional<Users> newUser=userRepository.findById(id);
        if(newUser.isEmpty())  throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));

        if(checkAuth.Check(newUser.get())) {

            newUser.get().setEmail(users.getEmail());
            newUser.get().setPassword(users.getPassword());
            newUser.get().setName(users.getName());
            newUser.get().setPhone(users.getPhone());
            return userRepository.save(newUser.get());
        }
        throw new AccessDeniedException("Unauthorized user");

    }

    public void deleteUser(int id) {

        Optional<Users> newUser=userRepository.findById(id);
        if(newUser.isEmpty())  throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));
        if(checkAuth.Check(newUser.get())) {
            userRepository.deleteById(id);
            return;
        }
        throw new AccessDeniedException("Unauthorized user");
    }

}