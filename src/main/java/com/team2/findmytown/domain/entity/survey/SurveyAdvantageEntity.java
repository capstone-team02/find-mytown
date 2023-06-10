package com.team2.findmytown.domain.entity.survey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "survey_advantage")
public class SurveyAdvantageEntity {

    @Id
    @Column(name = "advantage_id")
    private Integer id;

    @Column(name = "advantage_korname")
    private String advantageKor;

    @Column(name = "advantage_enname")
    private String advantageEn;
}
