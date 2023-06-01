package com.team2.findmytown.controller;

import com.team2.findmytown.dto.request.SurveyDTO;
import com.team2.findmytown.service.ChatGPTService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/mytown/v1/api/GPT")
public class ChatGptController {

    private final ChatGPTService chatGPTService;
    private final ChatgptService chatgptService;

    @PostMapping("/makeReview")
    public String makeReview(@RequestBody SurveyDTO SurveyDTO) {
        return chatGPTService.getChatMakeReview(SurveyDTO);
    }

    @PostMapping("/chatBot")
    public String test(@RequestBody String question) {
        return chatGPTService.getChatResponse(question);
    }
}
