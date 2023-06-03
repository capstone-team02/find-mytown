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

    public String getGptMakeReview(SurveyDTO surveyDTO) {

        String district = surveyDTO.getDistrict();
        List<String> mood = wordPostProcesser("mood", surveyDTO.getMood());
        List<String> advantage = wordPostProcesser("advantage", surveyDTO.getAdvantage());
        List<String> disadvantage = wordPostProcesser("disadvantage", surveyDTO.getDisadvantage());

        String prompt = "Within 180 bytes, please put the words I give you and write a review of the live in the borough." +
                "Answer in Korean and Please translate it naturally."
                + district + "mood is" + mood + ", advantage is" + advantage + ", disadvantage is" + disadvantage + ".";

        System.out.println(prompt);     //log 확인
        System.out.println(mood);
        System.out.println(advantage);
        System.out.println(disadvantage);

        return chatgptService.sendMessage(prompt);
    }

    public String getChatResponse(String question) {
        return chatgptService.sendMessage(question);
    }


    //매끄러운 최종 prompt를 위한 단어 후처리
    public List<String> wordPostProcesser (String category, List<String> words) {

        String before;
        List<String> after = new ArrayList<String>();

        //mood 단어
        if (category == "mood") {
            for (int i = 0; i < words.size(); i++) {
                before = words.get(i);
                if (before.equals("newlybuilt")) {
                    after.add(i, "there are many new built in the town");
                } else if (before.equals("learn")) {
                    after.add(i, "they have high enthusiasm for education");
                } else{
                    after.add(i, before);
                }
            }
            return after;
        }

        //advantage 단어
        else if (category == "advantage") {
            for (int i = 0; i < words.size(); i++) {
                before = words.get(i);
                if (before.equals("subway")) {
                    after.add(i, "it is close to the subway station");
                } else if (before.equals("bus")) {
                    after.add(i, "buses run often");
                } else if (before.equals("streetlight")) {
                    after.add(i, "here are many streetlights");
                } else if (before.equals("reverparkview")) {
                    after.add(i, "there are many parks and the view of the Han River");
                } else if (before.equals("pet")) {
                    after.add(i, " there are many pets and they are pets friendly");
                } else{
                    after.add(i, before);
                }
            }
            return after;
        }

        //idsadvantage 단어
        else {
            for (int i = 0; i < words.size(); i++) {
                before = words.get(i);
                if (before.equals("hardtraffic")) {
                    after.add(i, "transportation is inconvenient");
                } else if (before.equals("construction")) {
                    after.add(i, "construction is frequent");
                } else if (before.equals("uphill")) {
                    after.add(i, "there are lot of uphill paths");
                } else if (before.equals("lackrestaurant")) {
                    after.add(i, "lack of delicious restaurants");
                } else if (before.equals("floatingpeople")) {
                    after.add(i, "many floating people");
                } else{
                    after.add(i, before);
                }
            }
            return after;
        }
    }
}
