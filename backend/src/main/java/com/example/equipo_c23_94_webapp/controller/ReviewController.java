package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.dto.ReviewsDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.ReviewDtoReq;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Reviews;
import com.example.equipo_c23_94_webapp.mapper.ReviewMapper;
import com.example.equipo_c23_94_webapp.services.BooksService;
import com.example.equipo_c23_94_webapp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "*", "https://localhost/", "http://localhost:3000"}, methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
    RequestMethod.PUT }, allowedHeaders = { "Authorization", "Content-Type" })
@RequestMapping("/api/v1")
public class ReviewController {

    @Autowired
    private final BooksService booksService;
    private final ReviewService reviewService;

    public ReviewController(BooksService booksService, ReviewService reviewService) {
        this.booksService = booksService;
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewsDtoRes> getReviewById(@PathVariable Long id) {
        ReviewsDtoRes reviewsDtoRes = reviewService.getReview(id);
        return ResponseEntity.ok(reviewsDtoRes);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewsDtoRes>> findAll() {
        List<ReviewsDtoRes> reviewsDtoRes = reviewService.findAll();
        return ResponseEntity.ok(reviewsDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reviews")
    public ResponseEntity<ReviewsDtoRes> createReview(@RequestBody ReviewDtoReq reviewDtoReq) {

        Books book = booksService.findById(reviewDtoReq.bookId());
        Reviews review = ReviewMapper.toReview(reviewDtoReq, book);
        ReviewsDtoRes reviewsDtoRes = reviewService.createReview(review);

        booksService.updateBookBDA(book);
        book.addReview(review);
        return ResponseEntity.ok(reviewsDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/reviews/{id}")
    public ResponseEntity<ReviewsDtoRes> updateReview(@PathVariable Long id,
                                                 @RequestBody ReviewDtoReq reviewDtoReq) {
        ReviewsDtoRes reviewsDtoRes = reviewService.updateReview(id, reviewDtoReq);
        return ResponseEntity.ok(reviewsDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("La review con el id " + id + "fue eliminado correctamente");
    }
}
