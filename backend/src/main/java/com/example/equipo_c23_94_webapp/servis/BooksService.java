package com.example.equipo_c23_94_webapp.servis;


import com.example.equipo_c23_94_webapp.dto.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.dto.BookDtoReq;
import com.example.equipo_c23_94_webapp.dto.BookDtoRes;

import java.util.List;

public interface BooksService {

    BookDtoRes getBook(Long id);
    BookDtoRes createBook(BookDtoReq bookDtoReq);
    BookDtoRes updateBook(Long id, BookDtoReq bookDtoReq);
    void deleteAuthor(Long id);
    List<BookDtoRes> findAll();
}
