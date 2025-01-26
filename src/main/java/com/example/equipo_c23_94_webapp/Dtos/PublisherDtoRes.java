package com.example.equipo_c23_94_webapp.Dtos;
import java.util.List;

public record PublisherDtoRes(
        Long id,
        String name,
        String country,
        List<Long> bookIds
) {

    public static class Builder {
        private Long id;
        private String name;
        private String country;
        private List<Long> bookIds;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder bookIds(List<Long> bookIds) {
            this.bookIds = bookIds;
            return this;
        }

        public PublisherDtoRes build() {
            return new PublisherDtoRes(id, name, country, bookIds);
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
