package com.example.equipo_c23_94_webapp.services;


import com.example.equipo_c23_94_webapp.dto.AuthorDtoRes;
import com.example.equipo_c23_94_webapp.dto.CategoryDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.AuthorDtoReq;
import com.example.equipo_c23_94_webapp.dto.req.CategoryDtoReq;
import com.example.equipo_c23_94_webapp.entity.Authors;
import com.example.equipo_c23_94_webapp.entity.Categories;

import java.util.List;

public interface CategoryService{

    CategoryDtoRes getCategory(Long id);
    CategoryDtoRes createCategory(CategoryDtoReq categoryDtoReq);
    CategoryDtoRes updateCategory(Long id, CategoryDtoReq categoryDtoReq);
    void deleteCategory(Long id);
    List<CategoryDtoRes> findAll();
    Categories findById(Long id);
    void updateCategoryBDA(Categories category);
}
