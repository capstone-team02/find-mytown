package com.team2.findmytown.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

//@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name="survey")
public class SurveyEntity {

    @Id
    @Column(name = "survey_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long surveyId;

    private long userId;



    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="district_id")
    @ToString.Exclude
    private DistrictEntity districtEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mood_id")
    private MoodEntity moodEntity;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advantage_id")
    private AdvantageEntity advantageEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disadvantage_id")
    private DisadvantageEntity disadvantage;

    @Column(name = "recommend_gender")
    private boolean recommendGender;

    @Column(name = "recommend_age")
    private String recommendAge;

    @Column(name = "recommend_housing")
    private String recommendHousing;

    private int star;

    @Column(length = 50)
    private String review;



    /*
    @Builder
    public SurveyEntity(long surveyId, long userId, int age, long guId, long mood, long advantage, long disadvantage,
                        boolean recommendGender, String recommendAge, String recommendHousing, int star, String review){
        this.surveyId = surveyId;
        this.userId = userId;
        this.age = age;
        this.guId = guId;
        this.mood = mood;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.recommendGender = recommendGender;
        this.recommendAge = recommendAge;
        this.recommendHousing = recommendHousing;
        this.star = star;
        this.review = review;
    }

     */
}
