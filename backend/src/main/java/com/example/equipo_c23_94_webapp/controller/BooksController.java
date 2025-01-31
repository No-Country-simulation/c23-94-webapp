package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.dto.BookDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.entity.*;
import com.example.equipo_c23_94_webapp.mapper.BookMapper;
import com.example.equipo_c23_94_webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:3000" }, methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
                RequestMethod.PUT }, allowedHeaders = { "Authorization", "Content-Type" })
@RequestMapping("/api/v1")
public class BooksController {

    @Autowired
    private final BooksService booksService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;
    private final LoanService loanService;


    public BooksController(BooksService booksService, AuthorService authorService, CategoryService categoryService, PublisherService publisherService, LoanService loanService) {
        this.booksService = booksService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
        this.loanService = loanService;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDtoRes> getBookById(@PathVariable Long id) {
        Books book = booksService.findById(id);
        List<Loans> loansLibro = book.getLoan();
        loansLibro.forEach(l -> {
            if (l.getReturnDate().isEqual(LocalDate.now())) {
                book.setCopies(book.getCopies()+1);
                booksService.updateBookBDA(book);
            }
        });
        return ResponseEntity.ok(BookMapper.toDTO(book));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDtoRes>> findAll() {
        List<BookDtoRes> bookDtoRes = booksService.findAll();
        return ResponseEntity.ok(bookDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/books")
    public ResponseEntity<BookDtoRes> createBook(@RequestBody BookDtoReq bookDtoReq) {
        Authors author = authorService.findById(bookDtoReq.authorId());
        Categories category = categoryService.findById(bookDtoReq.categoryId());
        Publishers publisher = publisherService.findById(bookDtoReq.publisherId());
        Books book = BookMapper.toBook(bookDtoReq, author, publisher, category);
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());

        BookDtoRes bookDtoRes = booksService.createBook(book);

        author.addBook(book);
        category.addBook(book);
        publisher.addBook(book);

        authorService.updateAuthorBDA(author);
        categoryService.updateCategoryBDA(category);
        publisherService.updatePublisherBDA(publisher);

        return ResponseEntity.ok(bookDtoRes);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/books/{id}")
    public ResponseEntity<BookDtoRes> updateBook(@PathVariable Long id,
                                                     @RequestBody BookDtoReq bookDtoReq) {
        BookDtoRes bookDtoRes = booksService.updateBook(id, bookDtoReq);
        return ResponseEntity.ok(bookDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        booksService.deleteBook(id);
        return ResponseEntity.ok("El book con el id " + id + "fue eliminado correctamente");
    }
}
