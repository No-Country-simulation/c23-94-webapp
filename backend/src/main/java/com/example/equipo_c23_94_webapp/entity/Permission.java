package com.example.equipo_c23_94_webapp.entity;

/* we add the permissions with which the jwt token is generated according to the role */
public enum Permission {

    READ_ALL_USERS,

    READ_ONE_USER,

    SAVE_ONE_USER,

    UPDATE_ONE_USER,

    DELETE_ONE_USER,

    READ_ALL_RAZAS,

    READ_ONE_RAZAS,

    SAVE_ONE_RAZAS,

    UPDATE_ONE_RAZAS,

    DELETE_ONE_RAZAS,

    READ_ALL_PETS,

    READ_ONE_PETS,

    SAVE_ONE_PETS,

    UPDATE_ONE_PETS,

    DELETE_ONE_PETS,

    EMAIL,

    ISS
}