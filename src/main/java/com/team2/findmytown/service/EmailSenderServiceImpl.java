package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.domain.repository.UserRepository;
import com.team2.findmytown.dto.request.MailDTO;
import com.team2.findmytown.dto.request.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Component
@Configuration
public class EmailSenderServiceImpl implements EmailSenderService{

    @Autowired
    private UserRepository userRepository;
    private MailProperties properties = new MailProperties();
    private JavaMailSender javaMailSender = new JavaMailSenderImpl();
    @Autowired
    private UserService userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    // 비밀번호 찾기 이메일: 이메일 전송 & 임시 비밀번호 변경
    public MailDTO createMailAndChangePw(String userEmail) {
        String tempPw = getTempPw();

        MailDTO mail = new MailDTO();
        mail.setAddress(userEmail);
        mail.setTitle("[NADO] 임시 비밀번호 발급 이메일 입니다.");
        mail.setMessage("안녕하세요. 웹 서비스 NADO(나도)에서 보내는 임시 비밀 번호 발급 안내 이메일입니다. 회원님의 임시 비밀번호는 "
                + tempPw + " 입니다." + "로그인 후 원하는 비밀번호로 재변경 바랍니다.");
        updatePw(tempPw,userEmail);

        return mail;
    }

    //생성된 임시 비밀번호로 업데이트
    public void updatePw(String tempPw, String userEmail){
        UserEntity user = userRepository.findByEmail(userEmail);
        UserDTO userDTO = UserDTO.builder()
                .email(userEmail)
                .password(tempPw).build();

        if(user == null) {
            throw new RuntimeException("Can not find user info");
        }else if(userEmail == null){
            throw new RuntimeException("fill the email");
        }
        else{
            try {
                user = userService.updateUser(userDTO, passwordEncoder);
                //user.setPassword(tempPw);
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    //랜덤함수로 임시비밀번호 생성
    public String getTempPw(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        message.setFrom(properties.getUsername());
        message.setReplyTo(properties.getUsername());

        System.out.println("message" + message);
        System.out.println("전송 완료");

        javaMailSender.send(message);
    }
}
