package com.team2.findmytown.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkDTO {
    private String districtName;
    private String date;

    @Builder
    public BookmarkDTO(String districtName, String date){
        this.districtName = districtName;
        this.date = date;
    }
}
