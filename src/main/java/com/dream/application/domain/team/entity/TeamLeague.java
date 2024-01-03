package com.dream.application.domain.team.entity;

import com.dream.application.domain.league.entity.League;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "TEAM_LEAGUE")
@Getter
@AllArgsConstructor
public class TeamLeague {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    public TeamLeague(Team team, League league) {
        this.team = team;
        this.league = league;
    }

}
