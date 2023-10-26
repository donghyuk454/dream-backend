package com.dream.application.domain.team.entity;

import com.dream.application.domain.player.entity.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
