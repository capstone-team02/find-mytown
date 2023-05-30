package com.team2.findmytown.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name="survey")
public class SurveyEntity {

    @Id
    @Column(name = "survey_id")
    @GeneratedValue(generator = "system-uuid") //자동으로 할당
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String surveyId;

    @OneToOne(mappedBy = "survey")
    private UserEntity user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="district_id")
    @ToString.Exclude
    private DistrictEntity districtEntity;

    @Column(name="mood_id")
    @ElementCollection
    private List<String> mood = new ArrayList<String>();

    private String age;

    @Column(name = "advantage_id")
    @ElementCollection
    private List<String> advantage = new ArrayList<String>();

    @Column(name = "disadvantage_id")
    @ElementCollection
    private List<String> disadvantage = new ArrayList<String>();

    @Column(name = "recommend_gender")
    private Role recommendGender;

    @Column(name = "recommend_age")
    private String recommendAge;

    @Column(name = "recommend_housing")
    private String recommendHousing;

    private String star;

    @Column(length = 50)
    private String review;

    @Column
    private String gptReview;


    @Builder
    public SurveyEntity(UserEntity user, String age, DistrictEntity district, List<String> mood, List<String> advantage,
                        List<String> disadvantage, Role recommendGender, String recommendAge, String recommendHousing,
                        String star, String review,String gptReview){
        this.user = user;
        this.age = age;
        this.districtEntity = district;
        this.mood = mood;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.recommendGender = recommendGender;
        this.recommendAge = recommendAge;
        this.recommendHousing = recommendHousing;
        this.star = star;
        this.review = review;
        this.gptReview = gptReview;
    }
}