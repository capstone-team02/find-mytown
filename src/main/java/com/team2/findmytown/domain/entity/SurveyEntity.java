package com.team2.findmytown.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name="survey")
public class SurveyEntity {

    @Id
    @Column(name = "survey_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long surveyId;

    @Column(name = "age", nullable = false)
    private int age;

    //일대일 (1:1) 단방향
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gu_id", nullable = false)
    private long guId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mood_id", nullable = false)
    private int mood;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advantage_id", nullable = false)
    private int advantage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disadvantage_id", nullable = false)
    private int disadvantage;

    @Column(name = "recommend_gender", nullable = false)
    private boolean recommendGender;

    @Column(name = "recommend_age", nullable = false)
    private String recommendAge;

    @Column(name = "recommend_housing", nullable = false)
    private String recommendHousing;

    @Column(name = "star", nullable = false)
    private int star;

    @Column(name = "review", length = 50, nullable = true)
    private String review;


    @Builder
    public SurveyEntity(long surveyId, int age, long guId, int mood, int advantage, int disadvantage,
                        boolean recommendGender, String recommendAge, String recommendHousing, int star, String review){
        this.surveyId = surveyId;
        this.age = age;
        this.mood = mood;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.recommendGender = recommendGender;
        this.recommendAge = recommendAge;
        this.recommendHousing = recommendHousing;
        this.star = star;
        this.review = review;
    }
}


/*
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")},name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid") //자동으로 할당
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String username;

    private String password;
    private String nickname;

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능



    @Enumerated(EnumType.STRING)
    private Role role; //성별 (권한으로 설정)

    @Builder
    public UserEntity(String username, String email){
        this.email = email;
        this.username = username;
    }


*/