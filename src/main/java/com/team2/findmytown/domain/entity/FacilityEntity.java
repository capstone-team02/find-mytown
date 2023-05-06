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
    private long bank; //은행


    @Column(name="shopping_center")
    private long shoppingCenter; //쇼핑센터, 마트

    @Column(name = "education")
    private long education; //교육기관


    //음식점
    @OneToOne
    @JoinColumn(name="restaurant_Id")
    private RestaurantEntity restaurantEntity;




}

