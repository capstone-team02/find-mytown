package com.team2.findmytown.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name="surv_mood")
public class MoodEntity {

    @Id
    @JoinColumn(name = "mood_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long moodId;

    @Column(name = "noisy")
    private boolean noisy;

    @Column(name = "quiet")
    private boolean quiet;

    @Column(name = "energy")
    private boolean energy;

    @Column(name = "silence")
    private boolean silence;

    @Column(name = "newlybuilt")
    private boolean newlybuilt;

    @Column(name = "learn")
    private boolean learn;

    @Column(name = "relaxed")
    private boolean relaxed;

    @Builder
    public MoodEntity(long moodId, boolean noisy, boolean quiet, boolean energy,
                      boolean silence, boolean newlybuilt, boolean learn, boolean relaxed){
        this.moodId = moodId;
        this.noisy = noisy;
        this.quiet = quiet;
        this.energy = energy;
        this.silence = silence;
        this.newlybuilt = newlybuilt;
        this.learn = learn;
        this.relaxed = relaxed;
    }
}
