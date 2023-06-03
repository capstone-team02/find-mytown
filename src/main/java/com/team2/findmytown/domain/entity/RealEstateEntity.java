package com.team2.findmytown.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "real_estate")
@RequiredArgsConstructor
public class RealEstateEntity {

    @Id
    @GeneratedValue
    private Integer id;
    //건물명
    @Column(name = "building_name")
    private String buildingName;
    //자치구명
    @Column(name = "gu_name")
    private String guName;
    //법정동명
    @Column(name = "dong_name")
    private String dongName;
    //물건 금액
    private String price;
    //면적
    private String area;

    //건물용도
    @Column(name = "house_type")
    private String houseType;
    @Column(name = "sale_type")
    private String saleType;

    @Builder
    public RealEstateEntity(String buildingName, String guName, String dongName, String price, String area, String houseType, String saleType){
        this.buildingName = buildingName;
        this.guName = guName;
        this.dongName = dongName;
        this.price = price;
        this.area = area;
        this.houseType = houseType;
        this.saleType = saleType;
    }

}
