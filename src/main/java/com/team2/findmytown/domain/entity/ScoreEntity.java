package com.team2.findmytown.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="score")
public class ScoreEntity {
    @Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scoreId;

    @Column(name = "density_rank")
    private int densityRank; //밀집도

    @Column(name = "children_rank")
    private int childrenRank; // 어린이

    @Column(name = "teen_rank")
    private int teenRank; //10대

    @Column(name = "twenty_rank")
    private int twentyRank;

    @Column(name = "thirty_rank")
    private int thirtyRank;

    @Column(name = "fourty_rank")
    private int fourtyRank;

    @Column(name = "fif_six_rank")
    private int fifSixRank;

    @Column(name = "elder_rank")
    private int elderRank;

    @Column(name = "foriegn_rank")
    private int foriegnRank;


    @Column(name="hospital_rank")
    private int hospitalRank; //병원

    @Column(name= "pharmacy_rank")
    private int pharmacyRank; //약국

    @Column(name = "bank_rank")
    private int bankRank;

    @Column(name="shopping_center_rank")
    private int shoppingCenterRank;

    @Column(name = "education_rank")
    private int educationRank; //교육기관

    @Column(name="parking_rank")
    private int parkingRank;

    @Column(name="culture_rank")
    private int cultureRank; //예술, 문화 시설


    @Column(name = "childcare_rank")
    private int childcareRank;

    @Column(name="restaurant_rank")
    private int restaurantRank;

    @Column(name = "cafe_rank")
    private int cafeRank;

    @OneToOne(mappedBy = "scoreEntity")
    private DistrictEntity districtEntity;




}
