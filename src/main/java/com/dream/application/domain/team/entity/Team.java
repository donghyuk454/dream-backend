package com.dream.application.domain.team.entity;

import com.dream.application.common.entity.FootballEntity;
import com.dream.application.domain.player.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team extends FootballEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players;

    public Team(Integer fbaId, String code, String name) {
        this.fbaId = fbaId;
        this.code = code;
        this.name = name;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
        if (!player.getTeam().equals(this))
            player.changeTeam(this);
    }
}
