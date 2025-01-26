package com.example.equipo_c23_94_webapp.Dtos;

import java.time.LocalDate;

public record LoanDtoRes(
        Long id,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        String status,
        Long userId,
        Long bookId
) {

    public static class Builder {
        private Long id;
        private LocalDate loanDate;
        private LocalDate dueDate;
        private LocalDate returnDate;
        private String status;
        private Long userId;
        private Long bookId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder loanDate(LocalDate loanDate) {
            this.loanDate = loanDate;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder returnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder bookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public LoanDtoRes build() {
            return new LoanDtoRes(id, loanDate, dueDate, returnDate, status, userId, bookId);
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}
