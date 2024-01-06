package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.FootballEntity;
import com.dream.application.domain.league.entity.League;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    }
}
