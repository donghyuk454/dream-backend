package com.dream.application.domain.player.repository;

import com.dream.application.domain.player.entity.Player;
import com.dream.application.domain.player.entity.PlayerDetails;
import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private EntityManager em;

    // 쿼리 횟수 확인
    // 개선 : 2(1+1)회 -> 1회
    @Test
    @DisplayName("fetch join 을 사용해 Team 도 함께 조회합니다.")
    void findWithTeamById() {
        //given
        int teamFbaId = 1;
        String teamCode = "code";
        String teamName = "teamName";
        Team team = new Team(teamFbaId, teamCode, teamName);
        teamRepository.save(team);

        String playerName = "손흥민";
        int playerNumber = 2;
        Player player = new Player(new PlayerDetails(playerName, playerNumber), team);
        playerRepository.save(player);
        team.addPlayer(player);

        em.flush();
        em.clear();

        //when
        Optional<Player> result = playerRepository.findWithTeamById(player.getPlayerId());

        //then
        assertThat(result).isPresent();

        Player searchResult = result.get();
        assertThat(searchResult.getPlayerDetails().getName()).isEqualTo(playerName);
        assertThat(searchResult.getPlayerDetails().getNumber()).isEqualTo(playerNumber);

        Team searchTeam = searchResult.getTeam();
        assertThat(searchTeam.getCode()).isEqualTo(teamCode);
        assertThat(searchTeam.getName()).isEqualTo(teamName);
    }
}