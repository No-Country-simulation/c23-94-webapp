package com.example.equipo_c23_94_webapp.services;

import com.example.equipo_c23_94_webapp.Dtos.LoanDtoRes;
import com.example.equipo_c23_94_webapp.Dtos.req.LoanDtoReq;
import com.example.equipo_c23_94_webapp.entity.Loans;

import java.util.List;

public interface LoanService {
    LoanDtoRes getLoan(Long id);
    LoanDtoRes createLoan(Loans loan);
    LoanDtoRes updateLoan(Long id, LoanDtoReq loanDtoReq);
    void deleteLoan(Long id);
    List<LoanDtoRes> findAll();
    Loans findById(Long id);
    void updateLoanBDA(Loans loan);
}
