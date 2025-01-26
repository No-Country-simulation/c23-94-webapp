package com.example.equipo_c23_94_webapp.services.impl;

import com.example.equipo_c23_94_webapp.dto.ReviewsDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.ReviewDtoReq;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Reviews;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.mapper.ReviewMapper;
import com.example.equipo_c23_94_webapp.repository.BooksRepository;
import com.example.equipo_c23_94_webapp.repository.ReviewsRepository;
import com.example.equipo_c23_94_webapp.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImpl implements ReviewService {

    private final BooksRepository booksRepository;
    private final ReviewsRepository reviewsRepository;

    public ReviewServiceImpl(BooksRepository booksRepository, ReviewsRepository reviewsRepository) {
        this.booksRepository = booksRepository;
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public ReviewsDtoRes getReview(Long id) {
        return ReviewMapper.toDTO(reviewsRepository.findById(id).orElseThrow(() -> new NotFoundException("Review no encontrado")));
    }

    @Override
    public ReviewsDtoRes createReview(Reviews review) {
        reviewsRepository.save(review);
        return ReviewMapper.toDTO(review);
    }

    @Override
    public ReviewsDtoRes updateReview(Long id, ReviewDtoReq reviewDtoReq) {
        Reviews review = reviewsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Review no encontrado"));
        Books book = booksRepository.findById(reviewDtoReq.bookId()).orElseThrow(() ->
                new NotFoundException("No se encontro el book"));
        review.setComment(reviewDtoReq.comment());
        review.setCreated_at(reviewDtoReq.created_at());
        review.setRating(review.getRating());
        review.setBook(book);
        reviewsRepository.save(review);
        return ReviewMapper.toDTO(review);
    }

    @Override
    public void deleteReview(Long id) {
        Reviews review = reviewsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Review no encontrado"));
        reviewsRepository.delete(review);
    }

    @Override
    public List<ReviewsDtoRes> findAll() {
        List<Reviews> reviews = reviewsRepository.findAll();
        return reviews.stream().map(ReviewMapper::toDTO).collect(Collectors.toList());
    }

    public Reviews findById(Long id) {
        return reviewsRepository.findById(id).orElseThrow(null);
    }

    public void updateReviewBDA(Reviews review){
        this.reviewsRepository.save(review);
    }
}
