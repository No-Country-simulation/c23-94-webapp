package com.example.equipo_c23_94_webapp.dto;

import java.util.List;

public record AuthorDtoRes(
        Long id,
        String name,
        String lastName,
        String country,
        String biography,
        List<Long> bookIds
) {

    public static class Builder {
        private Long id;
        private String name;
        private String lastName;
        private String country;
        private String biography;
        private List<Long> bookIds;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder biography(String biography) {
            this.biography = biography;
            return this;
        }

        public Builder bookIds(List<Long> bookIds) {
            this.bookIds = bookIds;
            return this;
        }

        public AuthorDtoRes build() {
            return new AuthorDtoRes(id, name, lastName, country, biography, bookIds);
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
