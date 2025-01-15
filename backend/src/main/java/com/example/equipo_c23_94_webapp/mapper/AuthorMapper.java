package com.example.equipo_c23_94_webapp.mapper;


import com.example.equipo_c23_94_webapp.dto.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.entity.Authors;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorDtoRes toDTO(Authors author) {

        List<BookDto> bookDtos = author.getBooks().stream()
                .map(BookMapper::toDTO) // Usamos el método toDTO de BookMapper
                .collect(Collectors.toList());

        return new AuthorDtoRes(
                author.getId(),
                author.getName(),
                author.getLast_name(),
                author.getCountry(),
                author.getBiography(),
                bookDtos
        );
    }

    public static Authors toAuthor(AuthorDtoReq authorDtoReq) {
        List<Books> books = authorDtoReq.books().stream()
                .map(BookMapper::toBook) // Usamos el método toBook de BookMapper
                .collect(Collectors.toList());
        return new Authors(
                authorDtoReq.name(),
                authorDtoReq.last_name(),
                authorDtoReq.country(),
                authorDtoReq.biography(),
                books
        );
    }
}
