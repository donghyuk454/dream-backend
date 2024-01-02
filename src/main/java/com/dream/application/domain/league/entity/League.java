package com.dream.application.domain.league.entity;

import com.dream.application.common.entity.FootballEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public League(Integer fbaId, String name, String type, String logo) {
        this.fbaId = fbaId;
        this.name = name;
        this.type = type;
        this.logo = logo;
    }
}
