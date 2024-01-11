package com.dream.application.domain.team.repository;

import com.dream.application.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByFbaId(Integer fbaId);
}
