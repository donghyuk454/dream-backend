package com.dream.application.domain.league.entity;

import com.dream.application.common.entity.FootballEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LEAGUE")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class League extends FootballEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEAGUE_ID")
    private Long leagueId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "LOGO")
    private String logo;

    @Column(name = "COUNTRY")
    private String country;

    @OneToMany(mappedBy = "league")
    private Set<Broadcast> broadcast;

    public League(Integer fbaId, String name, String type, String logo, String country) {
        this.fbaId = fbaId;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.country = country;
    }
}
