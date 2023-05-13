package com.team2.findmytown.service;


import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.domain.repository.UserRepository;
import com.team2.findmytown.dto.request.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@Service
public class UserServiceImple implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserEntity createUser(UserEntity userEntity) {
        if(userEntity == null || userEntity.getEmail() == null ) {
            throw new RuntimeException("Invalid arguments");
        }
        if(userEntity.getPassword() == null){
            throw new RuntimeException("password must not be null");
        }
        final String email = userEntity.getEmail();
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(userEntity);
    }



    @Override
    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByEmail(email);

        //mathes 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password,originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }

    //이메일 중복 검사
    @Override
    public Boolean checkEmail(String email) {
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            return false;//이메일 중복
        }
        else return true; // 이메일 없음
    }

    @Override
    public Boolean checkNickName(String nickName) {
        if(userRepository.existsByNickname(nickName)) {
            log.warn("nickname already exists {}", nickName);
            return false;
        }
        else return true;
    }

    public UserEntity updateUser(UserDTO userDto, PasswordEncoder passwordEncoder) {
        try {
            UserEntity user = userRepository.findByEmail(userDto.getEmail());
                if (userDto.getNickname() != null)
                    user.setNickname(userDto.getNickname());
                if(userDto.getPassword() != null)
                    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            return userRepository.save(user);
        } catch (Exception e) {
            log.error("error: ", e);
        }
        return null;
    }

    @Transactional
    public void deleteUser(String userEmail) {
        if (userEmail == null) {
            throw new RuntimeException("not exist user info");
        } else {
            try {
                userRepository.deleteByEmail(userEmail);
            } catch (Exception e) {
                log.error("error: ", e);
            }
        }
    }
}


