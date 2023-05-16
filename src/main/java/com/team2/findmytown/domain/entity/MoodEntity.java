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
    @Column(name = "mood_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long moodId;

    @Column(name = "noisy", nullable = true)
    private boolean noisy;

    @Column(name = "quiet", nullable = true)
    private boolean quiet;

    @Column(name = "energy", nullable = true)
    private boolean energy;

    @Column(name = "silence", nullable = true)
    private boolean silence;

    @Column(name = "newlybuilt", nullable = true)
    private boolean newlybuilt;

    @Column(name = "learn", nullable = true)
    private boolean learn;

    @Column(name = "relaxed", nullable = true)
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
