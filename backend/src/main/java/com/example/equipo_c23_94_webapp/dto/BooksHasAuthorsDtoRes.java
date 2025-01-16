package com.example.equipo_c23_94_webapp.dto;

public record BooksHasAuthorsDtoRes(
        Long id,
        Long booksId,
        Long authorsId
) {

    public static class Builder {
        private Long id;
        private Long booksId;
        private Long authorsId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder booksId(Long booksId) {
            this.booksId = booksId;
            return this;
        }

        public Builder authorsId(Long authorsId) {
            this.authorsId = authorsId;
            return this;
        }

        public BooksHasAuthorsDtoRes build() {
            return new BooksHasAuthorsDtoRes(id, booksId, authorsId);
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
