package com.example.equipo_c23_94_webapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    // Relación con Books_has_Autors (Uno a Muchos) tabla PIVOTE
    @OneToMany(mappedBy = "book_id", cascade = CascadeType.ALL)
    private Books_has_Autors books_has_Autors;


    // Relación con Publishers (Muchos a Uno)
     @ManyToOne
     @JoinColumn(name = "publishers_id", nullable = false)
     private Publishers publisher;

    // Relación con Categories (Muchos a Uno)
     @ManyToOne
     @JoinColumn(name = "categories_id", nullable = false)
     private Categories category;

    // Relación con Authors (Muchos a Uno)
    @ManyToOne
    @JoinColumn(name = "autors_id", nullable = false)
    private Authors author;

    // Relación con Loans (Uno a Muchos)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Loans loan;

    // Relación con Reviews (Uno a Muchos)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    // Constructor privado para Builder
    private Books(Long id, String name, String publishedDate, int numberPages, String edition, Long isbn, String coverPhoto, int copies, Publishers publisher, Categories category, Authors author,
                  java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, List<Reviews> reviews) {
        this.id = id;
        this.name = name;
        this.publishedDate = publishedDate;
        this.numberPages = numberPages;
        this.edition = edition;
        this.isbn = isbn;
        this.coverPhoto = coverPhoto;
        this.copies = copies;
         this.publisher = publisher;
         this.category = category;
         this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reviews = reviews;
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
         private Publishers publisher;
         private Categories category;
         private Authors author;
        private java.time.LocalDateTime createdAt;
        private java.time.LocalDateTime updatedAt;
        private List<Reviews> reviews;

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

         public Builder publisher(Publishers publisher) {
             this.publisher = publisher;
             return this;
         }

         public Builder category(Categories category) {
             this.category = category;
             return this;
         }

         public Builder author(Authors author) {
             this.author = author;
             return this;
         }

        public Builder createdAt(java.time.LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(java.time.LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder reviews(List<Reviews> reviews) {
            this.reviews = reviews;
            return this;
        }

        // Método build() que construye la instancia de Books
        public Books build() {
            return new Books(id, name, publishedDate, numberPages, edition, isbn, coverPhoto, copies, publisher, category, author, createdAt, updatedAt, reviews);
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

    public Publishers getPublisher() {
        return publisher;
    }

    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

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

    public Loans getLoan() {
        return loan;
    }

    public void setLoan(Loans loan) {
        this.loan = loan;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
}
