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

@Table(name="chatHistory")
public class ChatHistoryEntity {

    @Id @Column(name = "mypage_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String chatHistoryId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String question;

    private String answer;

    @Builder
    public ChatHistoryEntity(UserEntity user, String question, String answer){
        this.user = user;
        this.question = question;
        this.answer = answer;
    }
}
