package com.example.equipo_c23_94_webapp.dto;


public record ReviewDtoReq(
        int rating,
        String comment,
        Long bookId
) {}
