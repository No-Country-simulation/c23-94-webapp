package com.example.equipo_c23_94_webapp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String comment;
    private java.time.LocalDate created_at;

    @ManyToOne
    @JoinColumn(name = "books_id", nullable = false)
    private Books book;

    public Reviews(int rating, String comment, LocalDate created_at, Books book) {
        this.rating = rating;
        this.comment = comment;
        this.created_at = created_at;
        this.book = book;
    }
}
