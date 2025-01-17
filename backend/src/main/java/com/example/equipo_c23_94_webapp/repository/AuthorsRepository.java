package com.example.equipo_c23_94_webapp.repository;

import com.example.equipo_c23_94_webapp.entity.Authors;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Long> {
    Optional<Authors> findByName(String name);
    Optional<Authors> findByCountry(String country);
    Optional<Authors> findByBiography(String biography);
}
