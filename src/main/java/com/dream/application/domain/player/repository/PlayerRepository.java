package com.dream.application.domain.player.repository;

import com.dream.application.domain.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p " +
            "LEFT JOIN FETCH p.team t " +
            "WHERE p.playerId = :playerId")
    Optional<Player> findWithTeamById(@Param("playerId") Long playerId);
}
