package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.dto.CategoryDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.req.CategoryDtoReq;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Categories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDtoRes toDTO(Categories category) {
        List<Long> bookIds = category.getBooks().stream()
                .map(Books::getId) // Convertimos Books a sus IDs
                .collect(Collectors.toList());

        return new CategoryDtoRes(
                category.getId(),
                category.getCategory(),
                category.getDescription(),
                bookIds
        );
    }

    public static Categories toCategory(CategoryDtoReq categoryDtoReq) {

        return new Categories(
                categoryDtoReq.category(),
                categoryDtoReq.description(),
                new ArrayList<>()// Lista vacia que se ira llenando a medida que le asociemos books
        );
    }
}
