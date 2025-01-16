package com.example.equipo_c23_94_webapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Books> books;

    public Categories(String category, String description) {
        this.category = category;
        this.description = description;
    }

    public Categories(String category, String description, List<Books> books) {
        this.category = category;
        this.description = description;
        this.books = books;
    }
}
