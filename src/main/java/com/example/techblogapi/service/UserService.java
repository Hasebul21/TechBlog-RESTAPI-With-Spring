package com.example.techblogapi.service;


import com.example.techblogapi.entity.Users;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;


import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public Iterable<Users> getAllUser() {

        return userRepository.findAll();
    }

    public Users getSingleUser(int id) {

        Optional<Users> checkUser=userRepository.findById(id);
        if(checkUser.isPresent()) return checkUser.get();
        throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));

    }

    public Users updateUser(int id, Users users) throws TransactionSystemException {

        Optional<Users> newUser=userRepository.findById(id);
        if(newUser.isEmpty())  throw new EntityNotFoundException(Users.class,"Email",String.valueOf(newUser.get().getEmail()));

        newUser.get().setEmail(users.getEmail());
        newUser.get().setPassword(users.getPassword());
        newUser.get().setName(users.getName());
        newUser.get().setPhone(users.getPhone());

        return userRepository.save(newUser.get());
    }

    public Users deleteUser(int id) {
        Optional<Users> newUser=userRepository.findById(id);
        if(newUser.isPresent()) {
            userRepository.deleteById(id);
            return newUser.get();
        }
        throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));
    }

}