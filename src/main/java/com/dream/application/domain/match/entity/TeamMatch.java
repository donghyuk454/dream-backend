package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TEAM_MATCH")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMatch extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_MATCH_ID")
    private TeamMatchId teamMatchId;

    @Column(name = "IS_HOME")
    private Boolean isHome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LINEUP_ID")
    private Lineup lineup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID", insertable = false, updatable = false)
    private Match match;
}
