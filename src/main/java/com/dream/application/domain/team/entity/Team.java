package com.dream.application.domain.team.entity;

import com.dream.application.domain.player.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEAM")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long teamId;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "LEAGUE")
    private League league;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
