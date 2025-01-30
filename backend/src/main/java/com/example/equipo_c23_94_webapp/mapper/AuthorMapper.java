package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Books;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class AuthorMapper {

    public static AuthorDtoRes toDTO(Authors author) {
        List<Long> bookIds = author.getBooks().stream()
                .map(Books::getId) // Convertimos Books a sus IDs
                .collect(Collectors.toList());

        return new AuthorDtoRes(
                author.getId(),
                author.getName(),
                author.getLastName(),
                author.getCountry(),
                author.getBiography(),
                bookIds
        );
    }

    public static Authors toAuthor(AuthorDtoReq authorDtoReq) {
        //List<Books> books = authorDtoReq.books().stream()
          //      .map(BookMapper::toBook) // Usamos el método toBook de BookMapper
            //    .collect(Collectors.toList());
        return new Authors(
                authorDtoReq.name(),
                authorDtoReq.lastName(),
                authorDtoReq.country(),
                authorDtoReq.biography(),
                new ArrayList<>()// Lista vacia que se ira llenando a medida que le asociemos books

               // books
        );
    }
}