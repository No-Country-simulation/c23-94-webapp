package com.example.equipo_c23_94_webapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.equipo_c23_94_webapp.entity.Books;

@Repository
public interface  BooksRepository extends JpaRepository<Books, Long> {
    List<Books> findByTitle(String title);
    List<Books> findByAuthor(String author);
    List<Books> findByGenre(String genre);
    List<Books> findByPublicationDate(LocalDate publicationDate);
    
    
}
