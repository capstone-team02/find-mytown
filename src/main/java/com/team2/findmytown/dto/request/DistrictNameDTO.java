package com.team2.findmytown.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DistrictNameDTO {

    private String guName;
    private List<String> dongName;
    //private String dongName;

    @Builder
    public DistrictNameDTO(String guName, List<String> dongName){
        this.guName = guName;
        this.dongName = dongName;
    }
}
