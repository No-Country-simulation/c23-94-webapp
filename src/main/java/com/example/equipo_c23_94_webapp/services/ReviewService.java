package com.example.equipo_c23_94_webapp.services;

import com.example.equipo_c23_94_webapp.Dtos.ReviewsDtoRes;
import com.example.equipo_c23_94_webapp.Dtos.req.ReviewDtoReq;
import com.example.equipo_c23_94_webapp.entity.Reviews;

import java.util.List;

public interface ReviewService {

    ReviewsDtoRes getReview(Long id);
    ReviewsDtoRes createReview(Reviews review);
    ReviewsDtoRes updateReview(Long id, ReviewDtoReq reviewDtoReq);
    void deleteReview(Long id);
    List<ReviewsDtoRes> findAll();
    Reviews findById(Long id);
    void updateReviewBDA(Reviews review);
}
