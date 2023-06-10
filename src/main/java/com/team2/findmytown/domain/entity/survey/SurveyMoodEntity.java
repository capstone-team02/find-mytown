package com.team2.findmytown.domain.entity.survey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "survey_mood")
public class SurveyMoodEntity {

    @Id
    @Column(name = "mood_id")
    private Integer id;

    @Column(name = "mood_korname")
    private String moodKor;

    @Column(name = "mood_enname")
    private String moodEn;
}
