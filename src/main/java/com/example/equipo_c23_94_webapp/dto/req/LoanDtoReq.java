package com.example.equipo_c23_94_webapp.dto.req;

import java.time.LocalDate;

public record LoanDtoReq(
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        String status,
        Long userId,
        Long bookId
) {}
