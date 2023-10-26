package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TeamMatch extends BaseEntity {

    private Boolean isHome;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @OneToOne
    private Lineup lineup;
}
