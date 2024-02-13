package com.dream.application.domain.match.repository;

import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMatchRepository extends JpaRepository<TeamMatch, Long> {
    boolean existsByTeamAndMatch(Team team, Match match); // 성능 개선 필요

    List<TeamMatch> findAllByTeam(Team team); // 성능 개선 필요
}
