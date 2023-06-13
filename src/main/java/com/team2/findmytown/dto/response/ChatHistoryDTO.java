package com.team2.findmytown.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatHistoryDTO {

    private String question;
    private String answer;

    @Builder
    public ChatHistoryDTO(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
}
