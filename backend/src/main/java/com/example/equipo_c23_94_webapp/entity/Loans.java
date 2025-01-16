package com.example.equipo_c23_94_webapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private java.time.LocalDate loan_date;
    private java.time.LocalDate due_date;
    private java.time.LocalDate return_date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "books_id", nullable = false)
    private Books book;

    public Loans(LocalDate loan_date, LocalDate due_date, LocalDate return_date, String status, Users user, Books book) {
        this.loan_date = loan_date;
        this.due_date = due_date;
        this.return_date = return_date;
        this.status = status;
        this.user = user;
        this.book = book;
    }
}
