package com.example.equipo_c23_94_webapp.repository;

import com.example.equipo_c23_94_webapp.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.List;

@Repository
public interface  UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email); // Método para encontrar un usuario por email
    List<Users> findByUsername(String username);


}
