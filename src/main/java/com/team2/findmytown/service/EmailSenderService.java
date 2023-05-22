package com.team2.findmytown.service;

import com.team2.findmytown.dto.request.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;

public interface EmailSenderService {
    public MailDTO createMailAndChangePw(String userEmail);
    public String getTempPw();
    void mailSend (MailDTO mailDTO);

}
