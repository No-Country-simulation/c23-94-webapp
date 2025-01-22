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
@RequestMapping("api/publishers")
public class PublishersController {

    @Autowired
    private final PublisherService service;

    public PublishersController(PublisherService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDtoRes> getPublisherById(@PathVariable Long id) {
        PublisherDtoRes publisher = service.getPublisher(id);
        return ResponseEntity.ok(publisher);
    }

    @GetMapping()
    public ResponseEntity<List<PublisherDtoRes>> findAll() {
        List<PublisherDtoRes> publisherDtoRes = service.findAll();
        return ResponseEntity.ok(publisherDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PublisherDtoRes> createPublisher(@RequestBody PublisherDtoReq publisherDtoReq) {
        PublisherDtoRes publisherDtoRes = service.createPublisher(publisherDtoReq);
        return ResponseEntity.ok(publisherDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublisherDtoRes> updatePublisher(@PathVariable Long id,
                                                         @RequestBody PublisherDtoReq publisherDtoReq) {
        PublisherDtoRes publisherDtoRes = service.updatePublisher(id, publisherDtoReq);
        return ResponseEntity.ok(publisherDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long id) {
        service.deletePublisher(id);
        return ResponseEntity.ok("La editora con el id " + id + "fue eliminado correctamente");
    }
}
