package com.team2.findmytown.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name ="gu_and_district")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class GuAndDistrictNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String guName;

    public String dongName;

    public Long guId;

    @Builder
    public GuAndDistrictNameEntity(String guName, String dongName, Long guId){
        this.guName = guName;
        this.dongName = dongName;
        this.guId = guId;
    }


}
