package com.example.equipo_c23_94_webapp.dto.req;


public record ReviewDtoReq(
        int rating,
        String comment,
        Long bookId
) {}
