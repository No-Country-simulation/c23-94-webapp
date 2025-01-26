package com.example.equipo_c23_94_webapp.services;

import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.entity.Authors;

import java.util.List;

public interface AuthorService {

    AuthorDtoRes getAuthor(Long id);
    AuthorDtoRes createAuthor(AuthorDtoReq authorDtoReq);
    AuthorDtoRes updateAuthor(Long id, AuthorDtoReq authorDtoReq);
    void deleteAuthor(Long id);
    List<AuthorDtoRes> findAll();
    Authors findById(Long id);
    void updateAuthorBDA(Authors author);
}
