package com.team2.findmytown.service;

import com.team2.findmytown.domain.entity.SurveyEntity;
import com.team2.findmytown.dto.request.SurveyDTO;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGPTService {

    private final ChatgptService chatgptService;

    public ChatGPTService(ChatgptService chatgptService) {
        this.chatgptService = chatgptService;
    }

    public String getChatMakeReview(SurveyDTO surveyDTO) {

        String district = surveyDTO.getDistrict();
        List<String> mood = surveyDTO.getMood();
        List<String> advantage = surveyDTO.getAdvantage();
        List<String> disadvantage = surveyDTO.getDisadvantage();

        String prompt = "Within 200 bytes, please put the words I give you and write a review of the town. Answer in Korean."
                + district + "," + mood + "," + advantage + "," + disadvantage + ".";

        System.out.println(prompt);     //log 확인

        return chatgptService.sendMessage(prompt);
    }

    public String getChatResponse(String question) {
        return chatgptService.sendMessage(question);
    }

}
