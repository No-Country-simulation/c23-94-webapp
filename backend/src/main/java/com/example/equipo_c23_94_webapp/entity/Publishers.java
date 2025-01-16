package com.example.equipo_c23_94_webapp.entity;


import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

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


}
