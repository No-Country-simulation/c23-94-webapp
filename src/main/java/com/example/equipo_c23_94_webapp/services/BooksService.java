package com.example.equipo_c23_94_webapp.services;


import com.example.equipo_c23_94_webapp.Dtos.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.Dtos.BookDtoRes;
import com.example.equipo_c23_94_webapp.entity.Books;

import java.util.List;

public interface BooksService {

    BookDtoRes getBook(Long id);
    BookDtoRes createBook(Books book);
    BookDtoRes updateBook(Long id, BookDtoReq bookDtoReq);
    void deleteBook(Long id);
    List<BookDtoRes> findAll();
    Books findById(Long id);
    void updateBookBDA(Books book);
}
