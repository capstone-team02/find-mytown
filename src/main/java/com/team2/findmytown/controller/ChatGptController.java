package com.team2.findmytown.controller;

import com.sun.xml.bind.v2.model.runtime.RuntimeEnumLeafInfo;
import com.team2.findmytown.config.SecurityUtil;
import com.team2.findmytown.domain.entity.ChatHistoryEntity;
import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.domain.repository.ChatHistoryRepository;
import com.team2.findmytown.domain.repository.UserRepository;
import com.team2.findmytown.dto.request.SurveyDTO;
import com.team2.findmytown.service.ChatGPTService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/mytown/v1/api/GPT")
public class ChatGptController {

    private final ChatGPTService chatGPTService;
    private final ChatgptService chatgptService;
    private final ChatHistoryRepository chatHistoryRepository;
    private final UserRepository userRepository;


    @PostMapping("/makeReview")
    public String makeReview(@RequestBody SurveyDTO SurveyDTO) {
        return chatGPTService.getGptMakeReview(SurveyDTO);
    }

    @PostMapping("/chatBot")
    public String test(@RequestBody Map<String, String> question) {

        String gptAnswer = chatGPTService.getChatResponse(question.get("question"));
        UserEntity user = userRepository.findByEmail(question.get("userEmail"));

        if(user == null){
            throw new RuntimeException("user info is null");
        }

        ChatHistoryEntity chatHistoryEntity = ChatHistoryEntity.builder()
                .user(user)
                .question(question.get("question")).answer(gptAnswer).build();
        chatHistoryRepository.save(chatHistoryEntity);

        return gptAnswer;
    }
}
