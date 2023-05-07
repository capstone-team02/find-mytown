package com.team2.findmytown.service;


import com.team2.findmytown.domain.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    public UserEntity createUser(final UserEntity userEntity);


    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder);






}
