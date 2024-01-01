package com.dream.application.domain.league.repository;

import com.dream.application.domain.league.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
    League findByFbaId(Integer fbaId);
}