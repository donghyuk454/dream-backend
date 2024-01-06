package com.dream.application.domain.team.repository;

import com.dream.application.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByFbaId(Integer fbaId);
}
