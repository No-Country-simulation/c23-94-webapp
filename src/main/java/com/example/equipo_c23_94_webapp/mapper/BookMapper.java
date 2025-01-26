package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.Dtos.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.Dtos.BookDtoRes;
import com.example.equipo_c23_94_webapp.entity.*;
import com.example.equipo_c23_94_webapp.repository.AuthorsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BookMapper {

    private final AuthorsRepository authorsRepository;

    public BookMapper(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }


    // Convertir una entidad Books a BookDto
    public static BookDtoRes toDTO(Books book) {
        return BookDtoRes.builder()
                .id(book.getId())
                .name(book.getName())
                .publishedDate(book.getPublishedDate())
                .numberPages(book.getNumberPages())
                .edition(book.getEdition())
                .isbn(book.getIsbn())
                .coverPhoto(book.getCoverPhoto())
                .copies(book.getCopies())
                .authorId(book.getAuthor().getId())
                .reviewsId(book.getReviews().stream().map(Reviews::getId).collect(Collectors.toList()))
                .loansId(book.getLoan().stream().map(Loans::getId).collect(Collectors.toList()))
                .publisherId(book.getPublisher().getId())
                .categoryId(book.getCategory().getId())
                .createdAt(book.getCreatedAt().toString())
                .updatedAt(book.getUpdatedAt().toString())

                .build();
    }

    // Convertir un BookDto a Books
    public static Books toBook(BookDtoReq book, Authors author, Publishers publisher, Categories category) {
        List<Reviews> reviews = new ArrayList<>();
        List<Loans> loans = new ArrayList<>();
        return Books.builder()
                .name(book.name())
                .publishedDate(book.publishedDate())
                .numberPages(book.numberPages())
                .edition(book.edition())
                .isbn(book.isbn())
                .coverPhoto(book.coverPhoto())
                .copies(book.copies())
                .createdAt(book.createdAt())
                .updatedAt(book.updatedAt())
                .author(author)
                .publisher(publisher)
                .category(category)
                .reviews(reviews)
                .loans(loans)
                .build();
    }
}
