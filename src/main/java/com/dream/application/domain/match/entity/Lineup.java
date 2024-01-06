package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.player.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LINEUP")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lineup extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINEUP_ID")
    private Long lineupId;

    @Column(name = "FORMATION")
    private String formation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Player> players;
}
