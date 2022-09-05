package com.example.techblogapi.service;


import com.example.techblogapi.entity.User;
import com.example.techblogapi.exception.EntityNotFoundException;
import com.example.techblogapi.repository.UserRepository;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;


import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUser() {

        return userRepository.findAll();
    }

    public User getSingleUser(int id) {

        Optional<User> checkUser=userRepository.findById(id);
        if(checkUser.isPresent()) return checkUser.get();
        throw new EntityNotFoundException(User.class,"id",String.valueOf(id));

    }

    public User updateUser(int id,User user) throws TransactionSystemException {

        Optional<User> newUser=userRepository.findById(id);
        if(newUser.isEmpty())  throw new EntityNotFoundException(User.class,"Email",String.valueOf(newUser.get().getEmail()));

        newUser.get().setEmail(user.getEmail());
        System.out.println("Hello");
        newUser.get().setPassword(user.getPassword());
        newUser.get().setName(user.getName());
        newUser.get().setPhone(user.getPhone());

        return userRepository.save(newUser.get());
    }

    public User deleteUser(int id) {
        Optional<User> newUser=userRepository.findById(id);
        if(newUser.isPresent()) {
            userRepository.deleteById(id);
            return newUser.get();
        }
        throw new EntityNotFoundException(User.class,"id",String.valueOf(id));
    }
}