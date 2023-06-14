package com.team2.findmytown.controller;

import com.team2.findmytown.config.SecurityUtil;
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
    public ResponseEntity userUpdate(@RequestBody Map<String, String> change){

        ResponseDTO responseDTO;
        String chngNickname = change.get("nickname");
        String chngPassword = change.get("password");

        if (SecurityUtil.getCurrentMemberId() == null){
            responseDTO = ResponseDTO.builder()
                    .error("can't find login Info").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
        else if (chngNickname == null && chngPassword == null){
            responseDTO = ResponseDTO.builder()
                    .error("fill the info want to change").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
        else {
            UserEntity user = userService.updateUser(chngNickname, chngPassword, passwordEncoder);
            return ResponseEntity.ok().body(user);
        }
    }

    @PutMapping("/userDelete")
    public ResponseEntity userDelete() {
        ResponseDTO responseDTO;
        String userId = SecurityUtil.getCurrentMemberId();

        if (userId == null){
            responseDTO = ResponseDTO.builder()
                    .error("Can't find login Info").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }else{
            userService.deleteUser();
            return ResponseEntity.ok().body("success Delete account of " + userId);
        }
    }

    @GetMapping("/chatHistory")
    public ResponseEntity chatHistory(){
        return ResponseEntity.ok().body(mypageService.importChatHistoryList());
    }


    @GetMapping("/mySurvey")
    public ResponseEntity mySurvey(){
        return ResponseEntity.ok().body(mypageService.importMySurvey());
    }



    @RequestMapping("/bookmark")
    public ResponseEntity myBookmark(HttpServletRequest request){
        return ResponseEntity.ok().body(mypageService.importMyBookmark());
    }

    @RequestMapping("/bookmark/delete")
    public ResponseEntity myBookmarkDelete(@RequestBody Map<String, String> districtName){
        mypageService.deleteMyBookmark(districtName.get("district"));
        return ResponseEntity.ok().body("delete success");
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
