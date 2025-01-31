package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.dto.LoanDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.LoanDtoReq;
import com.example.equipo_c23_94_webapp.entity.*;
import com.example.equipo_c23_94_webapp.mapper.LoanMapper;
import com.example.equipo_c23_94_webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "*", "https://localhost/", "http://localhost:3000"}, methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
    RequestMethod.PUT }, allowedHeaders = { "Authorization", "Content-Type" })
@RequestMapping("/api/v1")
public class LoansController {

    @Autowired
    private final BooksService booksService;
    private final LoanService loanService;
    private final UserServis userServis;

    public LoansController(BooksService booksService, LoanService loanService, UserServis userServis) {
        this.booksService = booksService;
        this.loanService = loanService;
        this.userServis = userServis;
    }


    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanDtoRes> getLoanById(@PathVariable Long id) {
        LoanDtoRes loanDtoRes = loanService.getLoan(id);
        return ResponseEntity.ok(loanDtoRes);
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanDtoRes>> findAll() {
        List<LoanDtoRes> loanDtoRes = loanService.findAll();
        return ResponseEntity.ok(loanDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/loans")
    public ResponseEntity<LoanDtoRes> createLoan(@RequestBody LoanDtoReq loanDtoReq) {

        Users user = userServis.findById(loanDtoReq.userId());
        Books book = booksService.findById(loanDtoReq.bookId());
        Loans loan = LoanMapper.toLoan(loanDtoReq, user, book);
        LoanDtoRes loanDtoRes = loanService.createLoan(loan);
        user.addLoan(loan);
        book.addLoan(loan);
        book.setCopies(book.getCopies()-1);
        userServis.updateUserBDA(user);
        booksService.updateBookBDA(book);
        return ResponseEntity.ok(loanDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/loans/{id}")
    public ResponseEntity<LoanDtoRes> updateLoan(@PathVariable Long id,
                                                 @RequestBody LoanDtoReq loanDtoReq) {
        LoanDtoRes loanDtoRes = loanService.updateLoan(id, loanDtoReq);
        return ResponseEntity.ok(loanDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok("El loan con el id " + id + "fue eliminado correctamente");
    }
}
