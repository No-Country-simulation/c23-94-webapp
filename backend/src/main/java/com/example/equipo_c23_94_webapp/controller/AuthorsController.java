package com.example.equipo_c23_94_webapp.controller;

import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.services.AuthorService;
import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "*", "https://localhost/", "http://localhost:3000" }, methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
                RequestMethod.PUT }, allowedHeaders = { "Authorization", "Content-Type" })
@RequestMapping("/api/v1")
public class AuthorsController {

    @Autowired
    private final AuthorService service;

    public AuthorsController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDtoRes> getAuthorById(@PathVariable Long id) {
        AuthorDtoRes author = service.getAuthor(id);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDtoRes>> findAll() {
        List<AuthorDtoRes> authorDtoRes = service.findAll();
        return ResponseEntity.ok(authorDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/authors")
    public ResponseEntity<AuthorDtoRes> createAuthor(@RequestBody AuthorDtoReq authorDtoReq) {
        AuthorDtoRes authorDtoRes = service.createAuthor(authorDtoReq);
        return ResponseEntity.ok(authorDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorDtoRes> updateAuthor(@PathVariable Long id,
    @RequestBody AuthorDtoReq authorDtoReq) {
        AuthorDtoRes authorDtoRes = service.updateAuthor(id, authorDtoReq);
        return ResponseEntity.ok(authorDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
        return ResponseEntity.ok("El autor con el id " + id + "fue eliminado correctamente");
    }

}
