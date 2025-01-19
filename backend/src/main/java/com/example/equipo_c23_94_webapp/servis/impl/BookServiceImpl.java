package com.example.equipo_c23_94_webapp.servis.impl;


import com.example.equipo_c23_94_webapp.dto.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.dto.BookDtoRes;
import com.example.equipo_c23_94_webapp.servis.BooksService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BooksService {

    @Override
    public BookDtoRes getBook(Long id) {
        return null;
    }

    @Override
    public BookDtoRes createBook(BookDtoReq bookDtoReq) {
        return null;
    }

    @Override
    public BookDtoRes updateBook(Long id, BookDtoReq bookDtoReq) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {

    }

    @Override
    public List<BookDtoRes> findAll() {
        return List.of();
    }
}
