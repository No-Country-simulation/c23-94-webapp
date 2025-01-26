package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.dto.PublisherDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.PublisherDtoReq;
import com.example.equipo_c23_94_webapp.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "*", "https://localhost/" }, methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
    RequestMethod.PUT }, allowedHeaders = { "Authorization", "Content-Type" })
@RequestMapping("/api/v1")
public class PublishersController {

    @Autowired
    private final PublisherService service;

    public PublishersController(PublisherService service) {
        this.service = service;
    }

    @GetMapping("/publishers/{id}")
    public ResponseEntity<PublisherDtoRes> getPublisherById(@PathVariable Long id) {
        PublisherDtoRes publisher = service.getPublisher(id);
        return ResponseEntity.ok(publisher);
    }

    @GetMapping("/publishers")
    public ResponseEntity<List<PublisherDtoRes>> findAll() {
        List<PublisherDtoRes> publisherDtoRes = service.findAll();
        return ResponseEntity.ok(publisherDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/publishers")
    public ResponseEntity<PublisherDtoRes> createPublisher(@RequestBody PublisherDtoReq publisherDtoReq) {
        PublisherDtoRes publisherDtoRes = service.createPublisher(publisherDtoReq);
        return ResponseEntity.ok(publisherDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/publishers/{id}")
    public ResponseEntity<PublisherDtoRes> updatePublisher(@PathVariable Long id,
                                                         @RequestBody PublisherDtoReq publisherDtoReq) {
        PublisherDtoRes publisherDtoRes = service.updatePublisher(id, publisherDtoReq);
        return ResponseEntity.ok(publisherDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/publishers/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long id) {
        service.deletePublisher(id);
        return ResponseEntity.ok("La editora con el id " + id + "fue eliminado correctamente");
    }
}
