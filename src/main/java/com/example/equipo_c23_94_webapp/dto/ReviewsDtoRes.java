package com.example.equipo_c23_94_webapp.dto;

import java.time.LocalDate;

public record ReviewsDtoRes(
        Long id,
        int rating,
        String comment,
        LocalDate createdAt,
        Long bookId
) {

    public static class Builder {
        private Long id;
        private int rating;
        private String comment;
        private LocalDate createdAt;
        private Long bookId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder createdAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder bookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public ReviewsDtoRes build() {
            return new ReviewsDtoRes(id, rating, comment, createdAt, bookId);
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
