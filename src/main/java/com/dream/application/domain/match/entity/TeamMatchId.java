package com.dream.application.domain.match.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TeamMatchId implements Serializable {
    @Column(name = "TEAM_ID")
    private Long teamId;
    @Column(name = "MATCH_ID")
    private Long matchId;
}
