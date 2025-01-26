package com.example.equipo_c23_94_webapp.mapper;

import com.example.equipo_c23_94_webapp.Dtos.UserDtoRes;
import com.example.equipo_c23_94_webapp.Dtos.req.UserDtoReq;
import com.example.equipo_c23_94_webapp.entity.Users;

public class UserMapper {

    public static UserDtoRes toDTO(Users user) {
        return UserDtoRes.builder()
                .id(user.getId())
                .lastName(user.getLast_name())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress()).build();

               // .userEnum(user.getUserEnum() != null ? user.getUserEnum().toString() : null)

    }

    public static Users toUserEntity(UserDtoReq request) {
       // UserEnum userEnum = request.getUserEnum() != null ? UserEnum.valueOf(request.getUserEnum()) : null;
        return Users.builder()
                .password(request.password())
                .username(request.username())
                .email(request.email())
             //   .userEnum(userEnum)
                .build();
    }
}
