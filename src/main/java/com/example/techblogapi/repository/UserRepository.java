package com.example.techblogapi.repository;

import com.example.techblogapi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    public Optional<User> findByEmail(String email);
    public Boolean existsByEmail(String email);

}