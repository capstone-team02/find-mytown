package com.team2.findmytown.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewListDTO {
    private String nickname;
    private String totalReview;

    @Builder
    public ReviewListDTO(String nickname, String totalReview){
        this.nickname = nickname;
        this.totalReview = totalReview;
    }
}
