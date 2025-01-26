package com.example.equipo_c23_94_webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.equipo_c23_94_webapp.entity.Reviews;

@Repository
public interface   ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findByRating(int rating);
}
