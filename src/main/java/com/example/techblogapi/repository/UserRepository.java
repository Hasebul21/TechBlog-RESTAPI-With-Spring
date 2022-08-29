package com.example.techblogapi.repository;

import com.example.techblogapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmail(String email);
    public Boolean existsByEmail(String email);

}
