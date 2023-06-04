package com.team2.findmytown.service;

import com.team2.findmytown.config.SecurityUtil;
import com.team2.findmytown.domain.entity.ChatHistoryEntity;
import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.domain.repository.ChatHistoryRepository;
import com.team2.findmytown.domain.repository.UserRepository;
import com.team2.findmytown.dto.response.ChatHistoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MypageServiceImpl {
    @Autowired
    private ChatHistoryRepository chatHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    public List<ChatHistoryDTO> importChatHistoryList(String userEmail) {

        List<ChatHistoryEntity> chatHistories;
        List<ChatHistoryDTO> chatHistoryList = new ArrayList<>();

        UserEntity user = userRepository.findByEmail(userEmail);
        ChatHistoryDTO chatHistoryDTO;

        if(userEmail.isEmpty()){
            throw new RuntimeException("Login User Info is Null");
        }else{
            chatHistories = chatHistoryRepository.findAllByUser(user);

            for(int i = 0; i < chatHistories.size(); i++){
                chatHistoryDTO = ChatHistoryDTO.builder()
                        .question(chatHistories.get(i).getQuestion())
                        .answer(chatHistories.get(i).getAnswer()).build();

                chatHistoryList.add(chatHistoryDTO);
            }
            return chatHistoryList;
        }
    }
}
