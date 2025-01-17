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

    @Column(name = "loan_date")
    private java.time.LocalDate loanDate;
    private java.time.LocalDate due_date;

    @Column(name = "return_date")
    private java.time.LocalDate returnDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "books_id", nullable = false)
    private Books book;

    public Loans(LocalDate loanDate, LocalDate due_date, LocalDate returnDate, String status, Users user, Books book) {
        this.loanDate = loanDate;
        this.due_date = due_date;
        this.returnDate = returnDate;
        this.status = status;
        this.user = user;
        this.book = book;
    }
}
