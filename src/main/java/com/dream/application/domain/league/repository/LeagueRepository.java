package com.dream.application.domain.league.repository;

import com.dream.application.domain.league.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Long> {
    Optional<League> findByFbaId(Integer fbaId);
}