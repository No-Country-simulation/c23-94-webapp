package com.example.equipo_c23_94_webapp.entity;

import java.util.Arrays;
import java.util.List;

public enum Role {

    /* permission assignment by role */
    CLIENT(Arrays.asList(
            Permission.READ_ONE_USER, // Ver su propia información
            Permission.UPDATE_ONE_USER, // Actualizar su propia información
            Permission.READ_ALL_CATEGORIES, // Ver todas las categorías
            Permission.READ_ONE_CATEGORY, // Ver una categoría específica
            Permission.READ_ALL_BOOKS, // Ver todos los libros
            Permission.READ_ONE_BOOK, // Ver un libro específico
            Permission.SAVE_ONE_BOOK, // Crear un libro como autor
            Permission.UPDATE_ONE_BOOK, // Actualizar un libro propio
            Permission.DELETE_ONE_BOOK, // Eliminar un libro propio
            Permission.READ_ALL_AUTHORS, // Ver todos los autores
            Permission.READ_ONE_AUTHOR, // Ver un autor específico
            Permission.READ_ALL_REVIEWS, // Ver todas las reseñas
            Permission.READ_ONE_REVIEW, // Ver una reseña específica
            Permission.SAVE_ONE_REVIEW, // Crear una reseña
            Permission.READ_ALL_PUBLISHERS,
            Permission.SAVE_ONE_LOAN,
            Permission.READ_ALL_LOANS,
            Permission.READ_ONE_LOAN,
            Permission.READ_ONE_USER
    )),

    ADMIN(Arrays.asList(
            Permission.READ_ALL_USERS, // Ver todos los usuarios
            Permission.READ_ONE_USER, // Ver un usuario específico
            Permission.SAVE_ONE_USER, // Crear un usuario
            Permission.UPDATE_ONE_USER, // Actualizar un usuario
            Permission.DELETE_ONE_USER, // Eliminar un usuario
            Permission.READ_ALL_CATEGORIES, // Ver todas las categorías
            Permission.READ_ONE_CATEGORY, // Ver una categoría específica
            Permission.SAVE_ONE_CATEGORY, // Crear una categoría
            Permission.UPDATE_ONE_CATEGORY, // Actualizar una categoría
            Permission.DELETE_ONE_CATEGORY, // Eliminar una categoría
            Permission.READ_ALL_BOOKS, // Ver todos los libros
            Permission.READ_ONE_BOOK, // Ver un libro específico
            Permission.SAVE_ONE_BOOK, // Crear un libro
            Permission.UPDATE_ONE_BOOK, // Actualizar un libro
            Permission.DELETE_ONE_BOOK, // Eliminar un libro
            Permission.READ_ALL_AUTHORS, // Ver todos los autores
            Permission.READ_ONE_AUTHOR, // Ver un autor específico
            Permission.SAVE_ONE_AUTHOR, // Crear un autor
            Permission.UPDATE_ONE_AUTHOR, // Actualizar un autor
            Permission.DELETE_ONE_AUTHOR, // Eliminar un autor
            Permission.READ_ALL_LOANS, // Ver todos los préstamos
            Permission.READ_ONE_LOAN, // Ver un préstamo específico
            Permission.SAVE_ONE_LOAN, // Crear un préstamo
            Permission.UPDATE_ONE_LOAN, // Actualizar un préstamo
            Permission.DELETE_ONE_LOAN, // Eliminar un préstamo
            Permission.READ_ALL_PUBLISHERS, // Ver todas las editoriales
            Permission.READ_ONE_PUBLISHER, // Ver una editorial específica
            Permission.SAVE_ONE_PUBLISHER, // Crear una editorial
            Permission.UPDATE_ONE_PUBLISHER, // Actualizar una editorial
            Permission.DELETE_ONE_PUBLISHER, // Eliminar una editorial
            Permission.READ_ALL_REVIEWS, // Ver todas las reseñas
            Permission.READ_ONE_REVIEW, // Ver una reseña específica
            Permission.SAVE_ONE_REVIEW, // Crear una reseña
            Permission.UPDATE_ONE_REVIEW, // Actualizar una reseña
            Permission.DELETE_ONE_REVIEW // Eliminar una reseña
    ));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
