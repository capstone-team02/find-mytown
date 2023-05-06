package com.team2.findmytown.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="district") //
public class DistrictEntity {
    @Id
    @Column(name = "district_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long districtId;


    @Column(name = "district_name",nullable = false)
    private String districtName;




    //인구 분포
    @OneToOne
    @JoinColumn(name="population_id")
    private PopulationEntity populationEntity;


    //구:동 = 1대 다 관계
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gu_id")
    @ToString.Exclude
    private GuEntity guEntity;



    /*
    //동: 설문조사 = 1: 다 관계
    @JsonManagedReference
    @OneToMany(mappedBy ="districtEntity",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<SurveyEntity> surveyEntities = new ArrayList<>();
     */

}
