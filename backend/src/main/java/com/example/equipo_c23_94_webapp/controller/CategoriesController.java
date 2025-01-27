package com.example.equipo_c23_94_webapp.controller;


import com.example.equipo_c23_94_webapp.dto.CategoryDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.CategoryDtoReq;
import com.example.equipo_c23_94_webapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "*", "https://localhost/", "http://localhost:3000" }, methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
    RequestMethod.PUT }, allowedHeaders = { "Authorization", "Content-Type" })
@RequestMapping("/api/v1")
public class CategoriesController {


    @Autowired
    private final CategoryService service;

    public CategoriesController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDtoRes> getCategoryById(@PathVariable Long id) {
        CategoryDtoRes category = service.getCategory(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDtoRes>> findAll() {
        List<CategoryDtoRes> categoryDtoRes = service.findAll();
        return ResponseEntity.ok(categoryDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/categories")
    public ResponseEntity<CategoryDtoRes> createCategory(@RequestBody CategoryDtoReq categoryDtoReq) {
        CategoryDtoRes categoryDtoRes = service.createCategory(categoryDtoReq);
        return ResponseEntity.ok(categoryDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDtoRes> updateCategory(@PathVariable Long id,
                                                     @RequestBody CategoryDtoReq categoryDtoReq) {
        CategoryDtoRes categoryDtoRes = service.updateCategory(id, categoryDtoReq);
        return ResponseEntity.ok(categoryDtoRes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return ResponseEntity.ok("La categoría con el id " + id + "fue eliminado correctamente");
    }
}
