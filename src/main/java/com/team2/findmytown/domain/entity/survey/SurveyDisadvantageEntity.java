package com.team2.findmytown.domain.entity.survey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "survey_disadvantage")
public class SurveyDisadvantageEntity {
    @Id
    @Column(name = "disadvantage_id")
    private Integer id;

    @Column(name = "disadvantage_korname")
    private String disadvantageKor;

    @Column(name = "disadvantage_enname")
    private String disadvantageEn;

}
