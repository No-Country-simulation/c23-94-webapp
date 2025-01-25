package com.example.equipo_c23_94_webapp.controller;

import com.example.equipo_c23_94_webapp.dto.UserDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.UserDtoReq;
import com.example.equipo_c23_94_webapp.services.UserServis;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserServis userServis;

    public UsersController(UserServis userServis) {
        this.userServis = userServis;
    }

    @PostMapping
    public ResponseEntity<UserDtoRes> createUser(@Valid @RequestBody UserDtoReq userDto) {
        UserDtoRes createdUser = userServis.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDtoRes> getUserById(@PathVariable Long id) {
        UserDtoRes user = userServis.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoRes> updateUser(@PathVariable Long id, @Valid @RequestBody UserDtoReq userDTOReq) {
        UserDtoRes updatedUser = userServis.updateUser(id, userDTOReq);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServis.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDtoRes>> getAllUsers() {
        List<UserDtoRes> users = userServis.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

