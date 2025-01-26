package com.example.equipo_c23_94_webapp.services.impl;


import com.example.equipo_c23_94_webapp.Dtos.req.BookDtoReq;
import com.example.equipo_c23_94_webapp.Dtos.BookDtoRes;
import com.example.equipo_c23_94_webapp.entity.*;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.mapper.BookMapper;
import com.example.equipo_c23_94_webapp.repository.*;
import com.example.equipo_c23_94_webapp.services.BooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BooksService {

    private final BooksRepository booksRepository;
    private final PublishersRepository publisherRepo;
    private final CategoriesRepository categoriesRepository;
    private final AuthorsRepository authorsRepository;
    private final LoansRepository loansRepository;
    private final ReviewsRepository reviewsRepository;

    public BookServiceImpl(BooksRepository booksRepository, PublishersRepository publisherRepo, CategoriesRepository categoriesRepository, AuthorsRepository authorsRepository, LoansRepository loansRepository, ReviewsRepository reviewsRepository) {
        this.booksRepository = booksRepository;
        this.publisherRepo = publisherRepo;
        this.categoriesRepository = categoriesRepository;
        this.authorsRepository = authorsRepository;
        this.loansRepository = loansRepository;
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public Books findById(Long id) {
        return booksRepository.findById(id).orElseThrow(null);
    }

    @Override
    public BookDtoRes getBook(Long id) {
        return BookMapper.toDTO(booksRepository.findById(id).orElseThrow(null));
    }

    @Override
    public BookDtoRes createBook(Books book) {
        booksRepository.save(book);
        return BookMapper.toDTO(book);
    }

    @Override
    public BookDtoRes updateBook(Long id, BookDtoReq bookDtoReq) {
        Books book = booksRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book no encontrado"));
        Publishers publisher = publisherRepo.findById(bookDtoReq.publisherId()).orElseThrow(
                () -> new NotFoundException("Publisher no encontrado"));
        Categories category = categoriesRepository.findById(bookDtoReq.categoryId()).orElseThrow(
                () -> new NotFoundException("Category no encontrado"));
        Authors author = authorsRepository.findById(bookDtoReq.authorId()).orElseThrow(
                () -> new NotFoundException("Author no encontrado"));
        List<Loans> loans = new ArrayList<>();
        List<Reviews> reviews = new ArrayList<>();

        bookDtoReq.loansId().forEach(l -> {
            Loans loan = loansRepository.findById(l).orElseThrow(null);
            loans.add(loan);
        });
        bookDtoReq.loansId().forEach(l -> {
            Reviews review = reviewsRepository.findById(l).orElseThrow(null);
            reviews.add(review);
        });

        book.setName(bookDtoReq.name());
        book.setPublishedDate(bookDtoReq.publishedDate());
        book.setNumberPages(bookDtoReq.numberPages());
        book.setEdition(bookDtoReq.edition());
        book.setIsbn(bookDtoReq.isbn());
        book.setCoverPhoto(bookDtoReq.coverPhoto());
        book.setCopies(bookDtoReq.copies());
        book.setCreatedAt(bookDtoReq.createdAt());
        book.setUpdatedAt(bookDtoReq.updatedAt());
        book.setPublisher(publisher);
        book.setCategory(category);
        book.setAuthor(author);
        book.setLoan(loans);
        book.setReviews(reviews);

        return BookMapper.toDTO(book);
    }

    @Override
    public void deleteBook(Long id) {
        Books book = booksRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book no encontrado"));
        booksRepository.delete(book);
    }

    @Override
    public List<BookDtoRes> findAll() {
        return booksRepository.findAll().stream().map(BookMapper::toDTO).toList();
    }

    @Override
    public void updateBookBDA(Books book) {
        booksRepository.save(book);
    }
}
