package com.team2.findmytown.service;


import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.domain.repository.UserRepository;
import com.team2.findmytown.dto.request.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@Service
public class UserServiceImple implements UserService{
    @Autowired
    private UserRepository userRepository;

    private JavaMailSender mailSender;

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
            return false;//이메일 중복
        }
        else return true; // 이메일 없음
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



    /*

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
        if(user == null) {
            throw new RuntimeException("Can not find user info");
        }else if(userEmail == null){
            throw new RuntimeException("fill the email");
        }
        else{
            try {
                user.setPassword(tempPw);
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

    // 메일보내기
    public void mailSend(MailDTO mailDTO) {
        System.out.println("전송 완료");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        message.setFrom("nado20231@gmail.com");
        message.setReplyTo("nado20231@gmail.com");
        System.out.println("message" + message);
        mailSender.send(message);
    }

     */
}


