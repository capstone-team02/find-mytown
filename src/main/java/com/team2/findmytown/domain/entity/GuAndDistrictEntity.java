package com.team2.findmytown.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "gu_district")
public class GuAndDistrictEntity {
    @Id
    @GeneratedValue
    public Integer id;

    public String gu_name;
    public String district_name;

    @Builder
    public GuAndDistrictEntity(Integer id, String gu_name, String district_name){
        this.id = id;
        this.gu_name = gu_name;
        this.district_name = district_name;
    }
}
