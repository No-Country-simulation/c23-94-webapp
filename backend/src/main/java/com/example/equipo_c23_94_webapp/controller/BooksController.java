package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.dto.BookDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Categories;
import com.example.equipo_c23_94_webapp.entity.Reviews;
import com.example.equipo_c23_94_webapp.mapper.AuthorMapper;
import com.example.equipo_c23_94_webapp.mapper.BookMapper;
import com.example.equipo_c23_94_webapp.services.AuthorService;
import com.example.equipo_c23_94_webapp.services.BooksService;

import com.example.equipo_c23_94_webapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BooksController {

    @Autowired
    private final BooksService booksService;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    public BooksController(BooksService booksService, AuthorService authorService, CategoryService categoryService) {
        this.booksService = booksService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDtoRes> getBookById(@PathVariable Long id) {
        BookDtoRes bookDtoRes = booksService.getBook(id);
        return ResponseEntity.ok(bookDtoRes);
    }

    @GetMapping()
    public ResponseEntity<List<BookDtoRes>> findAll() {
        List<BookDtoRes> bookDtoRes = booksService.findAll();
        return ResponseEntity.ok(bookDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookDtoRes> createBook(@RequestBody BookDtoReq bookDtoReq) {

        Authors author = authorService.findById(bookDtoReq.authorId());
        Categories category = categoryService.findById(bookDtoReq.categoryId());
        Books book = BookMapper.toBook(bookDtoReq, author);
        author.addBook(book);
        category.addBook(book);
        authorService.updateAuthorBDA(author);
        categoryService.updateCategoryBDA(category);
        BookDtoRes bookDtoRes = booksService.createBook(book);
        return ResponseEntity.ok(bookDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookDtoRes> updateBook(@PathVariable Long id,
                                                     @RequestBody BookDtoReq bookDtoReq) {
        BookDtoRes bookDtoRes = booksService.updateBook(id, bookDtoReq);
        return ResponseEntity.ok(bookDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        booksService.deleteBook(id);
        return ResponseEntity.ok("El book con el id " + id + "fue eliminado correctamente");
    }
}
