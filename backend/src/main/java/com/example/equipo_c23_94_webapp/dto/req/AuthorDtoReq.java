package com.example.equipo_c23_94_webapp.dto.req;

import com.example.equipo_c23_94_webapp.entity.Books;

import java.util.List;

public record AuthorDtoReq(String name, String lastName, String country, String biography, List<Books> books) {
}
