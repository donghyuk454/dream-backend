package com.dream.application.domain.match.repository;

import com.dream.application.domain.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByFbaId(Integer fbaId);
}
