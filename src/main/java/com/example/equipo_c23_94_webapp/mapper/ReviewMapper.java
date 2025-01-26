package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.Dtos.ReviewsDtoRes;
import com.example.equipo_c23_94_webapp.Dtos.req.ReviewDtoReq;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Reviews;


public class ReviewMapper {

    public static ReviewsDtoRes toDTO(Reviews reviews) {
        return new ReviewsDtoRes(
                reviews.getId(),
                reviews.getRating(),
                reviews.getComment(),
                reviews.getCreated_at(),
                reviews.getBook().getId()
        );
    }
    public static Reviews toReview(ReviewDtoReq reviewDtoReq, Books book) {
        return new Reviews(
                reviewDtoReq.rating(),
                reviewDtoReq.comment(),
                reviewDtoReq.created_at(),
                book
        );
    }
}
