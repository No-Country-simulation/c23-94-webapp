package com.example.equipo_c23_94_webapp.servis.impl;

import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.mapper.AuthorMapper;
import com.example.equipo_c23_94_webapp.repository.AuthorsRepository;
import com.example.equipo_c23_94_webapp.servis.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsRepository repository;

    public AuthorServiceImpl(AuthorsRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorDtoRes getAuthor(Long id) {
        return AuthorMapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("Autor no encontrado")));
    }

    @Override
    public AuthorDtoRes createAuthor(AuthorDtoReq authorDtoReq) {
        Authors author = AuthorMapper.toAuthor(authorDtoReq);
        repository.save(author);
        return AuthorMapper.toDTO(author);
    }

    @Override
    public AuthorDtoRes updateAuthor(Long id, AuthorDtoReq authorDtoReq) {
        Authors author = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Autor no encontrado"));
        author.setName(authorDtoReq.name());
        author.setLast_name(authorDtoReq.last_name());
        author.setCountry(authorDtoReq.country());
        author.setBiography(authorDtoReq.biography());
        repository.save(author);
        return AuthorMapper.toDTO(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Authors author = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Autor no encontrado"));
        repository.delete(author);
    }

    @Override
    public List<AuthorDtoRes> findAll() {
        List<Authors> authors = repository.findAll();
        return authors.stream().map(AuthorMapper::toDTO).collect(Collectors.toList());
    }

    public Authors findById(Long id) {
        return repository.findById(id).orElseThrow(null);
    }
}
