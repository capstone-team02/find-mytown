package com.team2.findmytown.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="gu")
public class GuEntity {
    @Id
    @Column(name = "gu_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guId;

    //구 이름
    @Column(name = "gu_name",nullable = false)
    private String guName;



    @Column(name="")
    // 구: 동 = 1:다 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "guEntity",fetch = FetchType.EAGER)
    @ToString.Exclude @Builder.Default
    private List<DistrictEntity> districtEntities = new ArrayList<>();

    /*
    @OneToMany(fetch = FetchType.LAZY)
    private long surveyGu;
    */


    //의료시설은 구 단위로 저장
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medical_id")
    private MedicalEntity medicalEntity;


    @Builder
    public GuEntity(List<DistrictEntity>districtEntities, String guName) {
        this.guName = guName;
        this.districtEntities = districtEntities;

    }
}

