package com.team2.findmytown.controller;


import com.team2.findmytown.domain.entity.ChatHistoryEntity;
import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.dto.request.MailDTO;
import com.team2.findmytown.dto.request.UserDTO;
import com.team2.findmytown.dto.response.ResponseDTO;
import com.team2.findmytown.security.TokenProvider;
import com.team2.findmytown.service.EmailSenderServiceImpl;
import com.team2.findmytown.service.MypageServiceImpl;
import com.team2.findmytown.service.UserServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mytown/v1/myPage")
public class MyPageController {

    @Autowired
    private UserServiceImple userService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private EmailSenderServiceImpl emailSenderService;
    @Autowired
    private MypageServiceImpl mypageService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


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

    @PostMapping("/chatHistory")
    public ResponseEntity chatHistory(@RequestBody Map<String, String> email){
        return ResponseEntity.ok().body(mypageService.importChatHistoryList(email.get("userEmail")));
    }

    @PostMapping("/mySurvey")
    public ResponseEntity mySurvey(@RequestBody Map<String, String> email){
        return ResponseEntity.ok().body(mypageService.importMySurvey(email.get("email")));
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
}
