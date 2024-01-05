package com.dream.application.domain.match.entity;

import com.dream.application.domain.league.entity.League;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MATCH_LEAGUE")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchLeague {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_LEAGUE_ID")
    private Long matchLeagueId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID")
    private Match match;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;
}
