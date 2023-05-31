package com.team2.findmytown.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DistrictNameDTO {

    private String guName;
    private String dongName;

    @Builder
    public DistrictNameDTO(String guName, String dongName){
        this.guName = guName;
        this.dongName = dongName;
    }
}
