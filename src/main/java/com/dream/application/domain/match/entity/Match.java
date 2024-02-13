package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.FootballEntity;
import com.dream.application.domain.league.entity.League;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MATCH")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Match extends FootballEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_ID")
    private Long matchId;

    @Column(name = "SCHEDULE")
    private LocalDateTime schedule;

    @OneToMany(mappedBy = "match")
    private Set<TeamMatch> teamMatches;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    public Match(Integer fbaId, LocalDateTime schedule, League league) {
        this.fbaId = fbaId;
        this.schedule = schedule;
        this.league = league;
        teamMatches = new HashSet<>();
    }

    public void addTeamMatch(TeamMatch teamMatch) {
        teamMatches.add(teamMatch);
        if (teamMatch.getMatch() == null) {
            teamMatch.setMatch(this);
        }
    }

    public void syncMatch(Match match) {
        this.schedule = match.getSchedule();
        this.league = match.league;
    }

    public String getTeamName(Boolean isHome) {
        for (TeamMatch teamMatch : teamMatches) {
            if (isHome.equals(teamMatch.getIsHome())) {
                return teamMatch.getTeam().getName();
            }
        }
        return null;
    }
}
