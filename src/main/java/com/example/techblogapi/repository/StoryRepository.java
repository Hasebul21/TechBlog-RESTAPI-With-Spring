package com.example.techblogapi.repository;

import com.example.techblogapi.entity.Storys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Storys,Integer> {


}
