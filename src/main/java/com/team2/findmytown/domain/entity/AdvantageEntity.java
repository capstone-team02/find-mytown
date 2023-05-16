package com.team2.findmytown.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name="surv_advantage")
public class AdvantageEntity {

    @Id
    @Column(name = "advantage_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long advantageId;

    @Column(name = "subway", nullable = true)
    private boolean subway;

    @Column(name = "bus", nullable = true)
    private boolean bus;

    @Column(name = "streetlight", nullable = true)
    private boolean streetlight;

    @Column(name = "security", nullable = true)
    private boolean security;

    @Column(name = "riverparkview", nullable = true)
    private boolean riverparkview;

    @Column(name = "clean", nullable = true)
    private boolean clean;

    @Column(name = "pet", nullable = true)
    private boolean pet;

    @Builder
    public AdvantageEntity(long advantageId, boolean subway, boolean bus, boolean streetlight,
                           boolean security, boolean riverparkview, boolean clean, boolean pet){
        this.advantageId = advantageId;
        this.subway = subway;
        this.bus = bus;
        this.streetlight = streetlight;
        this.security = security;
        this.riverparkview = riverparkview;
        this.clean = clean;
        this.pet = pet;
    }
}
