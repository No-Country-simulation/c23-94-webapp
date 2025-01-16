package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.dto.BookDto;
import com.example.equipo_c23_94_webapp.entity.Books;


public class BookMapper {


    // Convertir una entidad Books a BookDto
    public static BookDto toDTO(Books book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .publishedDate(book.getPublishedDate())
                .numberPages(book.getNumberPages())
                .edition(book.getEdition())
                .isbn(book.getIsbn())
                .coverPhoto(book.getCoverPhoto())
                .copies(book.getCopies())
                .createdAt(book.getCreatedAt().toString())
                .updatedAt(book.getUpdatedAt().toString())
                .build();
    }

    // Convertir un BookDto a Books
    public static Books toBook(Books book) {
        return Books.builder()
                .id(book.getId())
                .name(book.getName())
                .publishedDate(book.getPublishedDate())
                .numberPages(book.getNumberPages())
                .edition(book.getEdition())
                .isbn(book.getIsbn())
                .coverPhoto(book.getCoverPhoto())
                .copies(book.getCopies())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }
}
