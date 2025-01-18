package com.example.equipo_c23_94_webapp.dto;

import com.example.equipo_c23_94_webapp.entity.Reviews;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record BookDtoReq(
        String name,
        String publishedDate,
        int numberPages,
        String edition,
        Long isbn,
        String coverPhoto,
        int copies,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long publisherId,
        Long categoryId,
        Long authorId,
        List<Long> loansId,
        List<Reviews> reviewsId
        )
{}
