package com.team2.findmytown.domain.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name="bookmark")
public class BookmarkEntity {

    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String bookMarkId;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name="district_name")
    private DistrictEntity districtName;


    @Builder
    public BookmarkEntity(UserEntity user, DistrictEntity districtName){
        this.user = user;
        this.districtName = districtName;
    }
}