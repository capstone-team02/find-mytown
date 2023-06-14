package com.team2.findmytown.domain.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JoinColumn(name="district_id")
    private DistrictEntity district;

    @CreatedDate
    private LocalDate date;

    @Builder
    public BookmarkEntity(UserEntity user, DistrictEntity district){
        this.user = user;
        this.district = district;
    }
}