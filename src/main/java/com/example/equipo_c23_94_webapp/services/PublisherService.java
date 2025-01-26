package com.example.equipo_c23_94_webapp.services;

import com.example.equipo_c23_94_webapp.Dtos.PublisherDtoRes;
import com.example.equipo_c23_94_webapp.Dtos.req.PublisherDtoReq;
import com.example.equipo_c23_94_webapp.entity.Publishers;

import java.util.List;

public interface PublisherService {
    PublisherDtoRes getPublisher(Long id);
    PublisherDtoRes createPublisher(PublisherDtoReq publisherDtoReq);
    PublisherDtoRes updatePublisher(Long id, PublisherDtoReq publisherDtoReq);
    void deletePublisher(Long id);
    List<PublisherDtoRes> findAll();
    Publishers findById(Long id);
    void updatePublisherBDA(Publishers publishers);
}
