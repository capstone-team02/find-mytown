package com.team2.findmytown.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatGptResDTO {

    private String messages;
    private String answer;

    @Builder
    public ChatGptResDTO(String messages, String result, String answer){
        this.messages = messages;
        this.answer = answer;
    }
}
