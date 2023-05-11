package com.team2.findmytown.controller;

import com.team2.findmytown.service.ChatGPTService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/nado/v1/api/GPT")
public class ChatGptController {

    private final ChatGPTService chatGPTService;
    private final ChatgptService chatgptService;

    //chat-gpt 와 간단한 채팅 서비스 소스
    @PostMapping("makeReview")
    public String test(@RequestBody String question) {
        return chatGPTService.getChatResponse(question);
    }
}
