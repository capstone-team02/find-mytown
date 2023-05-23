package com.team2.findmytown.domain.entity;

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

    //@OneToOne(targetEntity = UserEntity.class, mappedBy = "surveyEntity")
    //@JoinColumn(name = "id")
    private long userId;

    private int age;

    //@ManyToOne(targetEntity = GuEntity.class)
    //@JoinColumn(name = "gu_id")
    private Long guId;

    @OneToOne(targetEntity = MoodEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "mood_id")
    private long mood;

    @OneToOne(targetEntity = AdvantageEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "advantage_id")
    private long advantage;

    @OneToOne(targetEntity = DisadvantageEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "disadvantage_id")
    private long disadvantage;

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