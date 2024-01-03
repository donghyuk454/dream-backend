package com.dream.application.domain.team.repository;

import com.dream.application.domain.team.entity.TeamLeague;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamLeagueRepository extends JpaRepository<TeamLeague, Long> {
}
