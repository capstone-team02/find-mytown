package com.team2.findmytown.dto.request;


import com.team2.findmytown.domain.entity.Role;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDTO {
    private String userEmail;
    private String district;
    private List<String> mood;
    private List<String> advantage;
    private List<String> disadvantage;
    private String age;
    private String star;
    private String review;
    private String gptReview;
    private String totalReview;
    //private Role recommendGender;
    //private String recommendAge;
    //private String recommendHousing;
    //private Boolean isFemale;
}
