package com.team2.findmytown.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="population")
public class PopulationEntity { //인구
    @Id
    @Column(name = "population_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long populationId;

    //어린이 0~10살
    @Column(name = "children")
    private long children;

    //10대
    @Column(name = "teen")
    private long teen;

    //20대
    @Column(name = "twenty")
    private long twenty;

    //30대
    @Column(name="thirty")
    private long thirty;

    //40대
    @Column(name="fourty")
    private long fourty;

    //5-60
    @Column(name="fif_six")
    private long fifSix;

    //70대 이상
    @Column(name = "elder")
    private long elder;

    //외국인
    @Column(name = "foriegn")
    private long forign;


    @Builder
    public PopulationEntity(long children, long teen, long twenty, long thirty, long fourty, long fifSix, long elder, long forign) {
        this.children = children;
        this.teen = teen;
        this.twenty = twenty;
        this.thirty = thirty;
        this.fourty = fourty;
        this.fifSix = fifSix;
        this.elder = elder;
        this.forign = forign;
    }
}

