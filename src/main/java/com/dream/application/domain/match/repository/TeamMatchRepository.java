package com.dream.application.domain.match.repository;

import com.dream.application.domain.match.entity.TeamMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMatchRepository extends JpaRepository<TeamMatch, Long> {
}
