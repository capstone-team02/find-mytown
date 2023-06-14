package com.team2.findmytown.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "population_density")
@DynamicUpdate
@DynamicInsert
@RequiredArgsConstructor
public class PopulationDensityEntity {
    @Id
    @GeneratedValue
    Integer id;

    String dongName;

    String population;

    String density;

    @Builder
    public PopulationDensityEntity(Integer id, String dongName, String population, String density){
        this.id = id;
        this.dongName = dongName;
        this.population = population;
        this.density  = density;
    }

}
