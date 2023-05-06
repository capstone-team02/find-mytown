package com.team2.findmytown.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="gu")
public class GuEntity {
    @Id
    @Column(name = "gu_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guId;

    //구 이름
    @Column(name = "gu_name",nullable = false)
    private String guName;

    // 구: 동 = 1:다 관계
    @JsonManagedReference
    @OneToMany(mappedBy = "guEntity",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<DistrictEntity> districtEntities = new ArrayList<>();


    @Builder
    public GuEntity(List<DistrictEntity>districtEntities, String guName) {
        this.guName = guName;
        this.districtEntities = districtEntities;

    }
}

