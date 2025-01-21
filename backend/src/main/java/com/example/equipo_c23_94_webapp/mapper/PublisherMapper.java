package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.dto.LoanDtoRes;
import com.example.equipo_c23_94_webapp.dto.PublisherDtoRes;
import com.example.equipo_c23_94_webapp.dto.ReviewsDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.LoanDtoReq;
import com.example.equipo_c23_94_webapp.dto.req.PublisherDtoReq;
import com.example.equipo_c23_94_webapp.dto.req.ReviewDtoReq;
import com.example.equipo_c23_94_webapp.entity.*;

import java.util.ArrayList;
import java.util.List;

public class PublisherMapper {

    public static PublisherDtoRes toDTO(Publishers publishers) {

        List<Long> booksIds = publishers.getBooks().stream().map(Books::getId).toList();
        return new PublisherDtoRes(
                publishers.getId(),
                publishers.getName(),
                publishers.getCountry(),
                booksIds
        );
    }
    public static Publishers toPublisher(PublisherDtoReq publisherDtoReq) {
        return new Publishers(
                publisherDtoReq.name(),
                publisherDtoReq.country(),
                new ArrayList<>()
        );
    }
}

