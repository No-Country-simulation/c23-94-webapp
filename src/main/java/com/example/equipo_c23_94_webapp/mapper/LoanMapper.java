package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.dto.LoanDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.req.LoanDtoReq;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Loans;
import com.example.equipo_c23_94_webapp.entity.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanMapper {

    public static LoanDtoRes toDTO(Loans loans) {

        return new LoanDtoRes(
                loans.getId(),
                loans.getLoanDate(),
                loans.getDue_date(),
                loans.getReturnDate(),
                loans.getStatus(),
                loans.getUser().getId(),
                loans.getBook().getId()
        );
    }
    public static Loans toLoan(LoanDtoReq loanDtoReq, Users user, Books book) {
        return new Loans(
                loanDtoReq.loanDate(),
                loanDtoReq.dueDate(),
                loanDtoReq.returnDate(),
                loanDtoReq.status(),
                user,
                book
        );
    }
}
