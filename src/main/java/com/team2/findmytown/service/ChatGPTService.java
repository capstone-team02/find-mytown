package com.team2.findmytown.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {

    private final ChatgptService chatgptService;

    public ChatGPTService(ChatgptService chatgptService) {
        this.chatgptService = chatgptService;
    }

    public String getChatResponse(String prompt) {
        // ChatGPT 에게 질문을 던집니다.
        return chatgptService.sendMessage(prompt);
    }

}
