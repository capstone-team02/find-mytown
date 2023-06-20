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

    public String guName;
    public String districtName;

    @Builder
    public GuAndDistrictEntity(Integer id, String guName, String districtName){
        this.id = id;
        this.guName = guName;
        this.districtName = districtName;
    }
}
