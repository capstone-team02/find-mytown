package com.team2.findmytown.controller;


import com.team2.findmytown.domain.entity.Role;
import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.dto.request.UserDTO;
import com.team2.findmytown.dto.response.ResponseDTO;
import com.team2.findmytown.security.TokenProvider;
import com.team2.findmytown.service.UserServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mytown/v1/auth")
public class UserController {
    @Autowired
    private UserServiceImple userService;

    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping("/checkEmail") //중복체크
    public ResponseEntity<?>checkEmail(@RequestBody String email){
        Boolean check = userService.checkEmail(email);
        return ResponseEntity.ok(check);
    }

    @GetMapping("/checkNickname")
    public ResponseEntity<?>checkNickname(@RequestBody String nickname){
        Boolean check = userService.checkNickName(nickname);
        return ResponseEntity.ok(check);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        try{
            if(userDTO == null || userDTO.getPassword()==null){
                throw new RuntimeException("Invelid Password value");
            }

            Role userRole;
            if(userDTO.getIsFemale()==true){
                userRole = Role.FEMALE;
            } else userRole = Role.MALE;

            //요청을 이용해 저장할 유저 만들기
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .role(userRole)
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

            //서비스를 이용해 리포지토리에 유저 저장
            UserEntity registerUser = userService.createUser(user);


            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registerUser.getEmail())
                    .id(registerUser.getId())
                    .role(userRole)
                    .username(registerUser.getUsername())
                    .build();
            //return ResponseEntity.ok().body(responseUserDTO);
            return ResponseEntity.ok(responseUserDTO);
        }catch (Exception e){
            //유저 정보는 항상 하나이므로 리스트가 아닌 그냥 UserDto 리턴
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){

        UserEntity user = userService.getByCredentials(
                userDTO.getEmail(),
                userDTO.getPassword(),
                passwordEncoder);


        if(user !=null){
            Role userRole = user.getRole();

            //토큰 생성
            final String token = tokenProvider.create(user);

            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .id(user.getId())
                    .token(token)
                    .role(userRole)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login faild.")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    






}
