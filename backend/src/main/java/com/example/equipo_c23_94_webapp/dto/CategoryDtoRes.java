package com.example.equipo_c23_94_webapp.dto;

import java.util.List;

public record CategoryDtoRes(
        Long id,
        String category,
        String description,
        List<Long> bookIds
) {

    public static class Builder {
        private Long id;
        private String category;
        private String description;
        private List<Long> bookIds;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder bookIds(List<Long> bookIds) {
            this.bookIds = bookIds;
            return this;
        }

        public CategoryDtoRes build() {
            return new CategoryDtoRes(id, category, description, bookIds);
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
