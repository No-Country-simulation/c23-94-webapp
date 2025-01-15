package com.example.equipo_c23_94_webapp.dto;

import java.util.List;

public record AuthorDtoReq(String name, String last_name, String country, String biography, List<Books> books) {
}
