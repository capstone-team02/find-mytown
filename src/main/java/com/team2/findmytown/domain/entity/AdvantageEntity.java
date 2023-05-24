package com.team2.findmytown.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="surv_advantage")
public class AdvantageEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "advantage_id")
    private long advantageId;

    @Column(name = "subway")
    private boolean subway;

    @Column(name = "bus")
    private boolean bus;

    @Column(name = "streetlight")
    private boolean streetlight;

    @Column(name = "security")
    private boolean security;

    @Column(name = "riverparkview")
    private boolean riverparkview;

    @Column(name = "clean")
    private boolean clean;

    @Column(name = "pet")
    private boolean pet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advantage_id")
    private SurveyEntity surveyId;


    /*
    @Builder
    public AdvantageEntity(long advantageId, boolean subway, boolean bus, boolean streetlight,
                           boolean security, boolean riverparkview, boolean clean, boolean pet, SurveyEntity survey){
        this.advantageId = advantageId;
        this.subway = subway;
        this.bus = bus;
        this.streetlight = streetlight;
        this.security = security;
        this.riverparkview = riverparkview;
        this.clean = clean;
        this.pet = pet;
    }
     */
}