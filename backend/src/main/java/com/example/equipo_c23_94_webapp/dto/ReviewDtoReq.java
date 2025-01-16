package com.example.equipo_c23_94_webapp.dto;

import java.time.LocalDate;

public record ReviewDtoReq(
        int rating,
        String comment,
        LocalDate createdAt,
        Long bookId
) {}
