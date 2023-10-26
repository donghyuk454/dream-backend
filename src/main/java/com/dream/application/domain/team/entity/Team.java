package com.dream.application.domain.team.entity;

import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.player.entity.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String name;

    @Enumerated(EnumType.STRING)
    private League league;

    @ManyToMany
    @JoinTable(name = "team_match",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id"))
    private Set<Match> matches;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;
}
