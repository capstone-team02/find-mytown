package com.team2.findmytown.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RealEstateDTO {
    private String area;
    private String buildingName;
    private String dongName;
    private String guName;
    private String houseType;
    private String price;
    private String saleType;

    @Builder
    public RealEstateDTO(String area, String buildingName, String dongName, String guName,
                         String houseType, String price, String saleType){
        this.area = area;
        this.buildingName = buildingName;
        this.dongName = dongName;
        this.guName = guName;
        this.houseType = houseType;
        this.price = price;
        this.saleType = saleType;
    }
}
