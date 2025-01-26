package com.example.equipo_c23_94_webapp.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.equipo_c23_94_webapp.entity.Books;

@Repository
public interface  BooksRepository extends JpaRepository<Books, Long> {
    List<Books> findByName(String name);
    List<Books> findByAuthor(Authors author);
    List<Books> findByCategory(Categories category);
    
}
