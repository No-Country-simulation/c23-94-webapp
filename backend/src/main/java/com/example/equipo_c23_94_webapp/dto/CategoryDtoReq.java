package com.example.equipo_c23_94_webapp.dto;

import java.util.List;

public record CategoryDtoReq(
        String category,
        String description,
        List<Long> bookIds
) {}
