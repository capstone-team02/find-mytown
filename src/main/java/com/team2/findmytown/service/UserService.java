package com.team2.findmytown.service;


import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.dto.request.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

public interface UserService {
    public UserEntity createUser(final UserEntity userEntity);


    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder);

    public Boolean checkEmail(final String email);

    public Boolean checkNickName(final String nickName);

    public UserEntity updateUser(final UserDTO userDto, final PasswordEncoder passwordEncoder);

    public UserDTO isLogin();

    public String randomNickname()throws IOException;

}
