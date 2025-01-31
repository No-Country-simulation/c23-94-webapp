package com.example.equipo_c23_94_webapp.dto;


import java.util.List;

public record BookDtoRes(
        Long id,
        String name,
        String publishedDate,
        int numberPages,
        String edition,
        Long isbn,
        String coverPhoto,
        int copies,
        Long publisherId,
        Long categoryId,
        Long authorId,
        String createdAt,
        String updatedAt,
        List<Long> loansId,
        List<Long> reviewsId
        ) {

    public static class Builder {
        private Long id;
        private String name;
        private String publishedDate;
        private int numberPages;
        private String edition;
        private Long isbn;
        private String coverPhoto;
        private int copies;
        private Long publisherId;
        private Long categoryId;
        private Long authorId;
        private List<Long> reviewsId;
        private List<Long> loansId;
        private String createdAt;
        private String updatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder publishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder numberPages(int numberPages) {
            this.numberPages = numberPages;
            return this;
        }

        public Builder edition(String edition) {
            this.edition = edition;
            return this;
        }

        public Builder isbn(Long isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder coverPhoto(String coverPhoto) {
            this.coverPhoto = coverPhoto;
            return this;
        }

        public Builder copies(int copies) {
            this.copies = copies;
            return this;
        }

        public Builder publisherId(Long publisherId) {
            this.publisherId = publisherId;
            return this;
        }

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder authorId(Long authorId) {
            this.authorId = authorId;
            return this;
        }
        public Builder reviewsId(List<Long> reviewsId) {
            this.reviewsId = reviewsId;
            return this;
        }
        public Builder loansId(List<Long> loansId) {
            this.loansId = loansId;
            return this;
        }

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public BookDtoRes build() {
            return new BookDtoRes(
                    id,
                    name,
                    publishedDate,
                    numberPages,
                    edition,
                    isbn,
                    coverPhoto,
                    copies,
                    publisherId,
                    categoryId,
                    authorId,
                    createdAt,
                    updatedAt,
                    loansId,
                    reviewsId
                    );
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
