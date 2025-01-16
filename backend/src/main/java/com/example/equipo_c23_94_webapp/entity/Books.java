package com.example.equipo_c23_94_webapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String publishedDate;
    private int numberPages;
    private String edition;

    @Column(unique = true)
    private Long isbn;

    private String coverPhoto;
    private int copies;

    @Column(name = "created_at", updatable = false, nullable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    // // Relación con Publishers (Muchos a Uno)
    // @ManyToOne
    // @JoinColumn(name = "publishers_id", nullable = false)
    // private Publishers publishers;

    // // Relación con Categories (Muchos a Uno)
    // @ManyToOne
    // @JoinColumn(name = "categories_id", nullable = false)
    // private Categories categories;

    // Relación con Authors (Muchos a Muchos)
    // @ManyToMany
    // @JoinTable(
    //     name = "books_has_authors",
    //     joinColumns = @JoinColumn(name = "books_id"),
    //     inverseJoinColumns = @JoinColumn(name = "authors_id")
    // )
    // private Collection<Authors> authors;

    // Constructor privado para Builder
    private Books(Long id, String name, String publishedDate, int numberPages, String edition, Long isbn, String coverPhoto, int copies, /*Publishers publishers, Categories categories, Collection<Authors> authors,*/ java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.publishedDate = publishedDate;
        this.numberPages = numberPages;
        this.edition = edition;
        this.isbn = isbn;
        this.coverPhoto = coverPhoto;
        this.copies = copies;
        // this.publishers = publishers;
        // this.categories = categories;
        // this.authors = authors;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Método estático builder() para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }

    // Clase Builder para Books
    public static class Builder {
        private Long id;
        private String name;
        private String publishedDate;
        private int numberPages;
        private String edition;
        private Long isbn;
        private String coverPhoto;
        private int copies;
        // private Publishers publishers;
        // private Categories categories;
        // private Collection<Authors> authors;
        private java.time.LocalDateTime createdAt;
        private java.time.LocalDateTime updatedAt;

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

        // public Builder publishers(Publishers publishers) {
        //     this.publishers = publishers;
        //     return this;
        // }

        // public Builder categories(Categories categories) {
        //     this.categories = categories;
        //     return this;
        // }

        // public Builder authors(Collection<Authors> authors) {
        //     this.authors = authors;
        //     return this;
        // }

        public Builder createdAt(java.time.LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(java.time.LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        // Método build() que construye la instancia de Books
        public Books build() {
            return new Books(id, name, publishedDate, numberPages, edition, isbn, coverPhoto, copies/* , publishers, categories, authors*/, createdAt, updatedAt);
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    // public Publishers getPublishers() {
    //     return publishers;
    // }

    // public void setPublishers(Publishers publishers) {
    //     this.publishers = publishers;
    // }

    // public Categories getCategories() {
    //     return categories;
    // }

    // public void setCategories(Categories categories) {
    //     this.categories = categories;
    // }

    // public Collection<Authors> getAuthors() {
    //     return authors;
    // }

    // public void setAuthors(Collection<Authors> authors) {
    //     this.authors = authors;
    // }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
