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
    @OneToOne(mappedBy = "advantage")
    @JoinColumn(name = "advantageId")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
