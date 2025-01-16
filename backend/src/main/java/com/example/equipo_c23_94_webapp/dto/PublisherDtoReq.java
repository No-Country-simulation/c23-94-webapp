package com.example.equipo_c23_94_webapp.dto;

import java.util.List;

public record PublisherDtoReq(
        String name,
        String country,
        List<Long> bookIds
) {}
