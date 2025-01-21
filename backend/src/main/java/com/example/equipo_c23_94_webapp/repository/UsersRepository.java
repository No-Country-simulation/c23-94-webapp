package com.example.equipo_c23_94_webapp.repository;

import com.example.equipo_c23_94_webapp.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface  UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email); // Método para encontrar un usuario por email
    Optional<Users> findByUsername(String username);


}
