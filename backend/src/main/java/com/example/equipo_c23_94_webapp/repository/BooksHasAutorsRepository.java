package com.example.equipo_c23_94_webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.equipo_c23_94_webapp.entity.Books_has_Autors;

@Repository
public interface  BooksHasAutorsRepository extends JpaRepository<Books_has_Autors, Long> {
    List<Books_has_Autors> findByBooksId(Long booksId);
    List<Books_has_Autors> findByAutorsId(Long autorsId);
    
}
