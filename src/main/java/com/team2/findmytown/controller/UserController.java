package com.team2.findmytown.controller;

import com.team2.findmytown.domain.entity.Role;
import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.dto.request.MailDTO;
import com.team2.findmytown.dto.request.UserDTO;
import com.team2.findmytown.dto.response.ResponseDTO;
import com.team2.findmytown.security.TokenProvider;
import com.team2.findmytown.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/mytown/v1/auth")
public class UserController {
    @Autowired
    private UserServiceImple userService;

    @Autowired
    private SurveyServiceImpl surveyService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/checkEmail") //중복체크
    public ResponseEntity<?>checkEmail(@RequestBody String email){
        Boolean check = userService.checkEmail(email);
        return ResponseEntity.ok(check);
    }

    @PostMapping("/checkNickname")
    public ResponseEntity<?>checkNickname(@RequestBody String nickname){
        Boolean check = userService.checkNickName(nickname);
        return ResponseEntity.ok(check);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        try{
            if(userDTO == null || userDTO.getPassword()==null){
                throw new RuntimeException("Invalid Password value");
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
                    .nickname(userService.randomNickname())
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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UserDTO userDTO) {
        String token = userDTO.getToken();

        //토큰 유효성 검사 후 setNull 처리
        if (tokenProvider.validateAndGetUserId(token) == null) {
            ResponseDTO responseDto = ResponseDTO.builder()
                    .error("Invalid token")
                    .build();
            return ResponseEntity.badRequest().body(responseDto);
        } else {
            //userDTO.setToken(null);
            return ResponseEntity.ok().body(userDTO);
        }
    }

    @PutMapping("/userUpdate")
    public ResponseEntity userUpdate(@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO;


        //토큰 유효성 검사 후 처리
        if (tokenProvider.validateAndGetUserId(userDTO.getToken()) == null) {
            responseDTO = ResponseDTO.builder()
                    .error("Invalid token")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        } else if (userDTO.getPassword() == null && userDTO.getNickname() == null) {
            responseDTO = ResponseDTO.builder()
                    .error("fill the info want to change")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        } else{
            UserEntity user = userService.updateUser(userDTO, passwordEncoder);
            return ResponseEntity.ok().body(user);
        }
    }

    @PutMapping("/userDelete")
    public ResponseEntity userDelete(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO;

        //토큰 유효성 검사 후 처리
        if (tokenProvider.validateAndGetUserId(userDTO.getToken()) == null) {
            responseDTO = ResponseDTO.builder()
                    .error("Invalid token")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }else{
            userService.deleteUser(userDTO.getEmail());
            return ResponseEntity.ok().body(userDTO);
        }
    }


    // 비밀번호 찾기 : 임시 비밀번호 발급 및 변경 처리
    @RequestMapping(value = "/sendEmail")
    public ResponseEntity findPwEmail(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO;
        String userEmail = userDTO.getEmail();

        if (userService.checkEmail(userEmail)) {
            responseDTO = ResponseDTO.builder()
                    .error("Email doesn't exist")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        } else {
            try {
                MailDTO mail = emailSenderService.createMailAndChangePw(userEmail);
                emailSenderService.mailSend(mail);

                return ResponseEntity.ok().body(mail);
            } catch (Exception e) {
                responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
                return ResponseEntity.badRequest().body(responseDTO);
            }
        }
    }

    @GetMapping("/loginUserInfo")
    public ResponseEntity<UserDTO> userInfo(HttpServletRequest request){ //@RequestHeader("Authorization") String token
        log.info(request.getHeader("Authorization"));
        return ResponseEntity.ok(userService.isLogin());
    }
}
