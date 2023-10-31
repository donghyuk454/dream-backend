package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "MATCH")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Match extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_ID")
    private Long matchId;

    @Column(name = "SCHEDULE")
    private LocalDateTime schedule;

    @OneToMany(mappedBy = "match")
    private Set<TeamMatch> teamMatches;
}
