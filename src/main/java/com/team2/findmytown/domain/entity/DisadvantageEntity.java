package com.team2.findmytown.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="surv_disadvantage")
public class DisadvantageEntity {

    @Id
    @OneToOne(mappedBy = "disadvantage")
    @JoinColumn(name = "disadvantage_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long disadvantageId;

    @Column(name = "hardtraffic")
    private boolean hardtraffic;

    @Column(name = "construction")
    private boolean construction;

    @Column(name = "uphill")
    private boolean uphill;

    @Column(name = "badsecurity")
    private boolean badsecurity;

    @Column(name = "lackrestaurant")
    private boolean lackrestaurant;

    @Column(name = "messy")
    private boolean messy;

    @Column(name = "floatingpopulation")
    private boolean floatingpeople;

    /*
    @Builder
    public DisadvantageEntity(long disadvantageId, boolean hardtraffic, boolean construction, boolean uphill,
                              boolean badsecurity, boolean lackrestaurant, boolean messy, boolean floatingpeople){
        this.disadvantageId = disadvantageId;
        this.hardtraffic = hardtraffic;
        this.construction = construction;
        this.uphill = uphill;
        this.badsecurity = badsecurity;
        this.lackrestaurant = lackrestaurant;
        this.messy = messy;
        this.floatingpeople = floatingpeople;
    }
     */
}












