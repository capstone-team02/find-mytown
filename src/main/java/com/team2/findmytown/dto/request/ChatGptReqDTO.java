package com.team2.findmytown.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ChatGptReqDTO implements Serializable {

    private  String model;
    private String prompt;
    private int maxTokens = 500;
    private Double temperature;
    private Double topP;

    @Builder
    public ChatGptReqDTO(String model, String prompt,
                         Integer maxTokens, Double temperature,
                         Double topP) {
        this.model = model;
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }
}
