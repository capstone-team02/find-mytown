package com.team2.findmytown.dto.request;


import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.Role;
import lombok.*;

@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDTO {
    private String userEmail;
    private String district;
    private String mood;
    private String advantage;
    private String disadvantage;
    private Role recommendGender;
    private String recommendAge;
    private String recommendHousing;
    private String age;
    private String star;
    private String review;
    private Boolean isFemale;
}
