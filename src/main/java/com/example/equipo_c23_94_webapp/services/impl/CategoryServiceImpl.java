package com.example.equipo_c23_94_webapp.services.impl;

import com.example.equipo_c23_94_webapp.dto.CategoryDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.CategoryDtoReq;
import com.example.equipo_c23_94_webapp.entity.Books;
import com.example.equipo_c23_94_webapp.entity.Categories;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.mapper.CategoryMapper;
import com.example.equipo_c23_94_webapp.repository.BooksRepository;
import com.example.equipo_c23_94_webapp.repository.CategoriesRepository;
import com.example.equipo_c23_94_webapp.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoriesRepository categoriesRepository;
    private final BooksRepository booksRepository;

    public CategoryServiceImpl(CategoriesRepository categoriesRepository, BooksRepository booksRepository) {
        this.categoriesRepository = categoriesRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    public CategoryDtoRes getCategory(Long id) {
        Categories category = categoriesRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category no encontrada"));
        return CategoryMapper.toDTO(category);
    }

    @Override
    public CategoryDtoRes createCategory(CategoryDtoReq categoryDtoReq) {
        Categories category = CategoryMapper.toCategory(categoryDtoReq);
        categoriesRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    @Override
    public CategoryDtoRes updateCategory(Long id, CategoryDtoReq categoryDtoReq) {
        List<Books> books = categoryDtoReq.bookIds().stream().map(l -> booksRepository.findById(l).orElseThrow(
                () -> new NotFoundException("No se encontró el  libro"))).toList();
        Categories category = categoriesRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No se encontró la categoria")
        );
        category.setCategory(categoryDtoReq.category());
        category.setDescription(category.getDescription());
        category.setBooks(books);
        categoriesRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Categories category = categoriesRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No se encontró la categoria")
        );
        categoriesRepository.delete(category);
    }

    @Override
    public List<CategoryDtoRes> findAll() {
        return categoriesRepository.findAll().stream().map(CategoryMapper::toDTO).toList();
    }

    @Override
    public Categories findById(Long id) {
        return categoriesRepository.findById(id).orElseThrow(() ->
            new NotFoundException("No se encontro la categoria"));
    }

    @Override
    public void updateCategoryBDA(Categories category) {
        categoriesRepository.save(category);
    }
}
