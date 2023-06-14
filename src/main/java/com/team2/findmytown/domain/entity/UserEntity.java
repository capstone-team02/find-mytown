package com.team2.findmytown.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")},name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid") //자동으로 할당
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String username;

    private String password;
    private String nickname;

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능

    @Enumerated(EnumType.STRING)
    private Role role; //성별 (권한으로 설정)

    @OneToOne
    @JoinColumn(name = "survey_id")
    private SurveyEntity survey;

    @Builder
    public UserEntity(String username, String email) {
        this.email = email;
        this.username = username;
    }
}