package com.example.equipo_c23_94_webapp.dto;

import java.util.Collection;

public record BookDto(
        Long id,
        String name,
        String publishedDate,
        int numberPages,
        String edition,
        Long isbn,
        String coverPhoto,
        int copies,
        String publishersName,
        String categoriesName,
        Collection<String> authorsNames,
        String createdAt,
        String updatedAt) {

    public static class Builder {
        private Long id;
        private String name;
        private String publishedDate;
        private int numberPages;
        private String edition;
        private Long isbn;
        private String coverPhoto;
        private int copies;
        private String publishersName;
        private String categoriesName;
        private Collection<String> authorsNames;
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

        public Builder publishersName(String publishersName) {
            this.publishersName = publishersName;
            return this;
        }

        public Builder categoriesName(String categoriesName) {
            this.categoriesName = categoriesName;
            return this;
        }

        public Builder authorsNames(Collection<String> authorsNames) {
            this.authorsNames = authorsNames;
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

        public BookDto build() {
            return new BookDto(
                    id,
                    name,
                    publishedDate,
                    numberPages,
                    edition,
                    isbn,
                    coverPhoto,
                    copies,
                    publishersName,
                    categoriesName,
                    authorsNames,
                    createdAt,
                    updatedAt
            );
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
