package com.dream.application.domain.match.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TeamMatchId {
    @Column(name = "TEAM_ID")
    private Long teamId;
    @Column(name = "MATCH_ID")
    private Long matchId;
}
