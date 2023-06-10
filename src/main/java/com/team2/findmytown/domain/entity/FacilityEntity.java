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
@Table(name="facility")
public class FacilityEntity {
    @Id
    @Column(name = "facility_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long facilityId;

    @Column(name = "bank")
    private int bank; //은행

    @Column(name="shopping_center")
    private int shoppingCenter; //쇼핑센터, 마트

    @Column(name = "education")
    private int education; //교육기관


    @Column(name="parking")
    private int parking; //주차장

    @Column(name="culture")
    private int culture; //예술, 문화 시설

    @Column(name = "childcare")
    private int childcare; // 보육시설

    @Column(name="restaurant")
    private int restaurant;

    @Column(name = "cafe")
    private int cafe;







}

