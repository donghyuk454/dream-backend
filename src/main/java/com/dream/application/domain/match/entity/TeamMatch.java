package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TEAM_MATCH")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatch extends BaseEntity {

    @EmbeddedId
    private TeamMatchId id;

    @Column(name = "IS_HOME")
    private Boolean isHome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LINEUP_ID")
    private Lineup lineup;

    @MapsId(value = "teamId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @MapsId(value = "matchId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID")
    private Match match;
}
