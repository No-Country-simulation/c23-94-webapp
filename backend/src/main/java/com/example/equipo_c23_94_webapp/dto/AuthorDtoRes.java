package com.example.equipo_c23_94_webapp.dto;


import java.util.List;

public record AuthorDtoRes(Long id, String name, String last_name, String country, String biography, List<BookDto> books) {
}
