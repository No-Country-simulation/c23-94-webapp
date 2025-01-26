package com.example.equipo_c23_94_webapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.equipo_c23_94_webapp.entity.Loans;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    List<Loans> findByLoanDate(LocalDate loanDate);
    List<Loans> findByReturnDate(LocalDate returnDate);
}