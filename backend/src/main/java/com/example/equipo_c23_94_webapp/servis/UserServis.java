package com.example.equipo_c23_94_webapp.servis;

import com.example.equipo_c23_94_webapp.dto.UserDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.UserDtoReq;

import java.util.List;

public interface UserServis {
    UserDtoRes getUser(Long id);

    UserDtoRes createUser(UserDtoReq userDTOReq);

    UserDtoRes updateUser(Long id, UserDtoReq userDTOReq);

    void deleteUser(Long id);

    List<UserDtoRes> getAllUsers();

    UserDtoRes updateProfileImage(Long id, String imageUrl);
}
