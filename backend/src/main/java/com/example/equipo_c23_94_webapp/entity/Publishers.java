package com.example.equipo_c23_94_webapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Publishers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Books> books;

    public Publishers(String name, String country, List<Books> books) {
        this.name = name;
        this.country = country;
        this.books = books;
    }

    public void addBook(Books book) {
        this.books.add(book);
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
