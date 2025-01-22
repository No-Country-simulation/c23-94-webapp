package com.example.equipo_c23_94_webapp.services.impl;

import com.example.equipo_c23_94_webapp.dto.LoanDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.LoanDtoReq;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Loans;
import com.example.equipo_c23_94_webapp.entity.Users;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.mapper.LoanMapper;
import com.example.equipo_c23_94_webapp.repository.BooksRepository;
import com.example.equipo_c23_94_webapp.repository.LoansRepository;
import com.example.equipo_c23_94_webapp.repository.UsersRepository;
import com.example.equipo_c23_94_webapp.services.LoanService;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoansRepository loansRepository;
    private final BooksRepository booksRepository;
    private final UsersRepository userRepository;

    public LoanServiceImpl(LoansRepository loansRepository, BooksRepository booksRepository, UsersRepository userRepository) {
        this.loansRepository = loansRepository;
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
    }


    @Override
    public LoanDtoRes getLoan(Long id) {
        Loans loans = loansRepository.findById(id).orElseThrow(() ->
                new NotFoundException("No se encontro el Loan"));
        return LoanMapper.toDTO(loans);
    }

    @Override
    public LoanDtoRes createLoan(Loans loan) {
        loansRepository.save(loan);
        return LoanMapper.toDTO(loan);
    }

    @Override
    public LoanDtoRes updateLoan(Long id, LoanDtoReq loanDtoReq) {
        Loans loans = loansRepository.findById(id).orElseThrow(() ->
                new NotFoundException("No se encontro el Loan"));
        Users user = userRepository.findById(loanDtoReq.userId()).orElseThrow(() ->
                new NotFoundException("No se encontro el user"));
        Books book = booksRepository.findById(loanDtoReq.bookId()).orElseThrow(() ->
                new NotFoundException("No se encontro el book"));
        loans.setLoanDate(loanDtoReq.loanDate());
        loans.setDue_date(loanDtoReq.dueDate());
        loans.setStatus(loanDtoReq.status());
        loans.setReturnDate(loanDtoReq.returnDate());
        loans.setBook(book);
        loans.setUser(user);
        return LoanMapper.toDTO(loans);
    }

    @Override
    public void deleteLoan(Long id) {
        Loans loans = loansRepository.findById(id).orElseThrow(() ->
                new NotFoundException("No se encontro el Loan"));
        loansRepository.delete(loans);
    }

    @Override
    public List<LoanDtoRes> findAll() {
        return loansRepository.findAll().stream().map(LoanMapper::toDTO).toList();
    }

    @Override
    public Loans findById(Long id) {
        return loansRepository.findById(id).orElseThrow(()->new NotFoundException("No se encontro el loan"));
    }

    @Override
    public void updateLoanBDA(Loans loan) {
        loansRepository.save(loan);
    }
}
