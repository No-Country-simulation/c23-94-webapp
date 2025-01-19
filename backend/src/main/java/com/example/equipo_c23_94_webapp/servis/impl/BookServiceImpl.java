package com.example.equipo_c23_94_webapp.servis.impl;


import com.example.equipo_c23_94_webapp.dto.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.dto.BookDtoRes;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.mapper.BookMapper;
import com.example.equipo_c23_94_webapp.repository.BooksRepository;
import com.example.equipo_c23_94_webapp.servis.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BooksService {

    @Autowired
    private final BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public BookDtoRes getBook(Long id) {
        return null;
    }

    @Override
    public BookDtoRes createBook(Books book) {
        booksRepository.save(book);
        return BookMapper.toDTO(book);
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
