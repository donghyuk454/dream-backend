package com.dream.application.domain.player.entity;

import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String name;
    private int number;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany(mappedBy = "subscribedPlayers")
    private Set<User> subscribers;
}
