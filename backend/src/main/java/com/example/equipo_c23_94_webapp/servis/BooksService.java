package com.example.equipo_c23_94_webapp.servis;


import com.example.equipo_c23_94_webapp.dto.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.dto.BookDtoRes;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Books;

import java.util.List;

public interface BooksService {

    BookDtoRes getBook(Long id);
    BookDtoRes createBook(Books book);
    BookDtoRes updateBook(Long id, BookDtoReq bookDtoReq);
    void deleteAuthor(Long id);
    List<BookDtoRes> findAll();
}
