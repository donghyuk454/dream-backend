package com.dream.application.domain.player.entity;

import com.dream.application.domain.team.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PLAYER")
@Getter
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    private Long playerId;

    @Embedded
    private PlayerDetails playerDetails;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Player(PlayerDetails details) {
        this.playerDetails = details;
    }

    public Player(PlayerDetails details, Team team) {
        this.playerDetails = details;
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        if (!team.getPlayers().contains(this))
            team.addPlayer(this);
    }
}
