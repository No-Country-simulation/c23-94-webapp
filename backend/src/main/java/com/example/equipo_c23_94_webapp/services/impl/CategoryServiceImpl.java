package com.example.equipo_c23_94_webapp.services.impl;

import com.example.equipo_c23_94_webapp.dto.CategoryDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.CategoryDtoReq;
import com.example.equipo_c23_94_webapp.entity.Categories;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.repository.CategoriesRepository;
import com.example.equipo_c23_94_webapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoriesRepository categoriesRepository;

    public CategoryServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public CategoryDtoRes getCategory(Long id) {
        Categories category = categoriesRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category no encontrada"));
        return null;
    }

    @Override
    public CategoryDtoRes createCategory(CategoryDtoReq categoryDtoReq) {
        return null;
    }

    @Override
    public CategoryDtoRes updateCategory(Long id, CategoryDtoReq categoryDtoReq) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public List<CategoryDtoRes> findAll() {
        return List.of();
    }

    @Override
    public Categories findById(Long id) {
        return null;
    }

    @Override
    public void updateCategoryBDA(Categories category) {

    }
}
