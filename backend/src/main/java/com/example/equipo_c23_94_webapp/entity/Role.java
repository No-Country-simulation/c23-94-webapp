package com.example.equipo_c23_94_webapp.entity;

import java.util.List;

import java.util.Arrays;

public enum Role {

    /* permission assignment by role */
    CLIENT(Arrays.asList(Permission.READ_ONE_USER, Permission.READ_ALL_RAZAS, Permission.UPDATE_ONE_USER,
            Permission.DELETE_ONE_USER, Permission.READ_ALL_PETS, Permission.READ_ONE_PETS, Permission.SAVE_ONE_PETS,
            Permission.UPDATE_ONE_PETS, Permission.DELETE_ONE_PETS)),

    ADMIN(Arrays.asList(Permission.READ_ALL_USERS, Permission.READ_ONE_USER, Permission.SAVE_ONE_USER,
            Permission.UPDATE_ONE_USER, Permission.DELETE_ONE_USER, Permission.READ_ALL_RAZAS,
            Permission.READ_ONE_RAZAS, Permission.SAVE_ONE_RAZAS, Permission.UPDATE_ONE_RAZAS,
            Permission.DELETE_ONE_RAZAS, Permission.READ_ALL_PETS, Permission.READ_ONE_PETS, Permission.SAVE_ONE_PETS,
            Permission.UPDATE_ONE_PETS, Permission.DELETE_ONE_PETS)),;

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
