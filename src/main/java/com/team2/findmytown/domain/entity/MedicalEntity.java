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
@Table(name="medical")
public class MedicalEntity {
    @Id
    @Column(name = "medical_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long populationId;

    @Column(name="hospital")
    private int hospital; //병원

    @Column(name= "pharmacy")
    private int pharmacy; //약국


}
