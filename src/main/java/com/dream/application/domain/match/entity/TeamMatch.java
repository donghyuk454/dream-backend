package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TEAM_MATCH")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatch extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_MATCH_ID")
    private Long teamMatchId;

    @Column(name = "IS_HOME")
    private Boolean isHome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LINEUP_ID")
    private Lineup lineup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID")
    private Match match;

    // lineup 이 아직 나오지 않은 경우
    public TeamMatch(boolean isHome, Team team, Match match) {
        this.isHome = isHome;
        this.team = team;
        setMatch(match);
    }

    // lineup 이 나온 경우
    public TeamMatch(boolean isHome, Lineup lineup, Team team, Match match) {
        this(isHome, team, match);
        this.lineup = lineup;
    }

    public void setMatch(Match match) {
        this.match = match;
        if (match.getTeamMatches() != null && !match.getTeamMatches().contains(this)) {
            match.addTeamMatch(this);
        }
    }
}
