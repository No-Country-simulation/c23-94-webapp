package com.example.equipo_c23_94_webapp.servis;

import com.example.equipo_c23_94_webapp.dto.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;

import java.util.List;

public interface AuthorService {

    AuthorDtoRes getAuthor(Long id);
    AuthorDtoRes createAuthor(AuthorDtoReq authorDtoReq);
    AuthorDtoRes updateAuthor(Long id, AuthorDtoReq authorDtoReq);
    void deleteAuthor(Long id);
    List<AuthorDtoRes> findAll();
}
