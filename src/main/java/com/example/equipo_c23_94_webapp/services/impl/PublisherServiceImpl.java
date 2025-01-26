package com.example.equipo_c23_94_webapp.services.impl;

import com.example.equipo_c23_94_webapp.Dtos.PublisherDtoRes;
import com.example.equipo_c23_94_webapp.Dtos.req.PublisherDtoReq;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Publishers;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.mapper.PublisherMapper;
import com.example.equipo_c23_94_webapp.repository.BooksRepository;
import com.example.equipo_c23_94_webapp.repository.PublishersRepository;
import com.example.equipo_c23_94_webapp.services.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublishersRepository publishersRepository;
    private  final BooksRepository booksRepository;

    public PublisherServiceImpl(PublishersRepository publishersRepository, BooksRepository booksRepository) {
        this.publishersRepository = publishersRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    public PublisherDtoRes getPublisher(Long id) {
        return PublisherMapper.toDTO(publishersRepository.findById(id).orElseThrow(() -> new NotFoundException("Publisher no encontrado")));
    }

    @Override
    public PublisherDtoRes createPublisher(PublisherDtoReq publisherDtoReq) {
        Publishers publisher = PublisherMapper.toPublisher(publisherDtoReq);
        publishersRepository.save(publisher);
        return PublisherMapper.toDTO(publisher);
    }

    @Override
    public PublisherDtoRes updatePublisher(Long id, PublisherDtoReq publisherDtoReq) {
        Publishers publisher = publishersRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Publisher no encontrado"));
        List<Books> books = publisherDtoReq.bookIds().stream().map(l -> booksRepository.findById(l).orElseThrow(
                () -> new NotFoundException("No se encontró el  libro"))).toList();
        publisher.setName(publisherDtoReq.name());
        publisher.setCountry(publisherDtoReq.country());
        publisher.setBooks(books);
        publishersRepository.save(publisher);
        return PublisherMapper.toDTO(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        Publishers publisher = publishersRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Publisher no encontrado"));
        publishersRepository.delete(publisher);
    }

    @Override
    public List<PublisherDtoRes> findAll() {
        List<Publishers> publishers = publishersRepository.findAll();
        return publishers.stream().map(PublisherMapper::toDTO).collect(Collectors.toList());
    }

    public Publishers findById(Long id) {
        return publishersRepository.findById(id).orElseThrow(null);
    }

    public void updatePublisherBDA(Publishers publisher){
        this.publishersRepository.save(publisher);
    }
}
