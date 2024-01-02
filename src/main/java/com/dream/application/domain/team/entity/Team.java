package com.dream.application.domain.team.entity;

import com.dream.application.common.entity.FootballEntity;
import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.player.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Long teamId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private League league;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public Team(Integer fbaId, String code, String name, League league) {
        this.fbaId = fbaId;
        this.code = code;
        this.name = name;
        this.league = league;
    }
}
