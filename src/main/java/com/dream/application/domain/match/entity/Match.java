package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
public class Match extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    private LocalDateTime schedule;

    @OneToMany(mappedBy = "match")
    private Set<TeamMatch> teamMatches;
}
