package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.ChatHistoryEntity;
import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.domain.repository.ChatHistoryRepository;
import com.team2.findmytown.domain.repository.SurveyRepository;
import com.team2.findmytown.domain.repository.UserRepository;
import com.team2.findmytown.dto.request.SurveyDTO;
import com.team2.findmytown.dto.response.ChatHistoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MypageServiceImpl implements MypageService{
    @Autowired
    private ChatHistoryRepository chatHistoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveyRepository surveyRepository;

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

    public Map<String, String> importMySurvey(String email){

        Map<String, String> mySurvey = new HashMap<>();
        SurveyEntity surveyEntity = surveyRepository.findAllByUserEmail(email);

        mySurvey.put("star", surveyEntity.getStar());
        mySurvey.put("review", surveyEntity.getReview());
        mySurvey.put("totalReview", surveyEntity.getTotalReview());

        return mySurvey;
    }
}
