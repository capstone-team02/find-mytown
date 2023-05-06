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
@Table(name= "restaurant")
public class RestaurantEntity {

    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantId;


    @Column(name = "food")
    private int food; //일반음식점

    @Column(name = "rest_cnt")
    private int restCnt; //음식점 수

    @Column(name = "cafe")
    private long cafe; //카페

}
