package com.example.equipo_c23_94_webapp.Dtos.req;


public record ReviewDtoReq(
        int rating,
        String comment,
        java.time.LocalDate created_at,
        Long bookId
) {}
