package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.dto.BookDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.mapper.BookMapper;
import com.example.equipo_c23_94_webapp.servis.AuthorService;
import com.example.equipo_c23_94_webapp.servis.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BooksController {

    @Autowired
    private final BooksService booksService;
    private final AuthorService authorService;

    public BooksController(BooksService booksService, AuthorService authorService) {
        this.booksService = booksService;
        this.authorService = authorService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookDtoRes> createBook(@RequestBody BookDtoReq bookDtoReq) {

        Authors author = authorService.findById(bookDtoReq.authorId());
        Books book = BookMapper.toBook(bookDtoReq, author);
        author.addBook(book);
        BookDtoRes bookDtoRes = booksService.createBook(book);
        return ResponseEntity.ok(bookDtoRes);
    }
}
