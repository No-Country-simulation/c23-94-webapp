package com.example.equipo_c23_94_webapp.services.impl;

import com.example.equipo_c23_94_webapp.dto.UserDtoRes;
import com.example.equipo_c23_94_webapp.dto.req.UserDtoReq;
import com.example.equipo_c23_94_webapp.entity.Users;
import com.example.equipo_c23_94_webapp.exceptions.NotFoundException;
import com.example.equipo_c23_94_webapp.mapper.UserMapper;
import com.example.equipo_c23_94_webapp.repository.UsersRepository;
import com.example.equipo_c23_94_webapp.services.UserServis;

import org.modelmapper.ModelMapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service

public class UserServisImpl implements UserServis {

    private final UsersRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServisImpl(UsersRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDtoRes getUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDtoRes getUserbyEmail(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDtoRes getUserbyUsername(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDtoRes createUser(UserDtoReq userDTOReq) {
        Users user = UserMapper.toUserEntity(userDTOReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encriptar la contraseña
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDtoRes updateUser(Long id, UserDtoReq userDTOReq) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        existingUser.setUsername(userDTOReq.username());
        existingUser.setPassword(passwordEncoder.encode(userDTOReq.password()));  // Encriptar la contraseña
        existingUser.setEmail(userDTOReq.email());
        //existingUser.setUserEnum(UserEnum.valueOf(userDTOReq.getUserEnum()));

        existingUser = userRepository.save(existingUser);
        return UserMapper.toDTO(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDtoRes> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDtoRes updateProfileImage(Long id, String imageUrl) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public Users findById(Long id) {
         return userRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void updateUserBDA(Users user) {
        userRepository.save(user);
    }
}
