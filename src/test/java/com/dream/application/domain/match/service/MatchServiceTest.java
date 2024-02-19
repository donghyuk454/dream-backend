package com.dream.application.domain.match.service;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.league.repository.LeagueRepository;
import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.match.repository.MatchRepository;
import com.dream.application.domain.match.repository.TeamMatchRepository;
import com.dream.application.domain.match.service.dto.request.MyPlayerMatchesServiceRequest;
import com.dream.application.domain.match.service.dto.response.MatchDto;
import com.dream.application.domain.match.service.dto.response.MyPlayerMatchesServiceResponse;
import com.dream.application.domain.player.entity.Player;
import com.dream.application.domain.player.entity.PlayerDetails;
import com.dream.application.domain.player.repository.PlayerRepository;
import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Autowired private LeagueRepository leagueRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private MatchRepository matchRepository;
    @Autowired private TeamMatchRepository teamMatchRepository;
    @Autowired private PlayerRepository playerRepository;
    @Autowired private EntityManager em;

    @Test
    @DisplayName("한국 선수의 id 로 선수가 속한 팀의 경기를 return 합니다.")
    void getMyPlayerMatches() {
        //given
        League league = new League(999, "Premier League", "type", "logo.png", "England");
        leagueRepository.save(league);

        Team team = new Team(100, "code", "토트넘");
        teamRepository.save(team);

        int matchCount = 10;
        addMatchesAndTeamMatches(league, team, matchCount);

        Player player = new Player(new PlayerDetails("손흥민", 7), team);
        playerRepository.save(player);
        Long playerId = player.getPlayerId();

        em.flush();
        em.clear();

        //when
        MyPlayerMatchesServiceRequest request = new MyPlayerMatchesServiceRequest(playerId, 2023);
        MyPlayerMatchesServiceResponse response = matchService.getMyPlayerMatches(request);

        //then
        assertThat(response.getPlayerId()).isEqualTo(playerId);
        assertThat(response.getMatches()).hasSize(matchCount);

        for (MatchDto matchdto: response.getMatches()) {
            assertThat(matchdto.getLeague()).isEqualTo(league.getName());
            List<String> teamNames = List.of(matchdto.getHome(), matchdto.getAway());
            assertThat(teamNames).contains(team.getName());
        }
    }

    private void addMatchesAndTeamMatches(League league, Team team, int matchCount) {
        for (int i = 0; i < matchCount; i++) {
            Match match = new Match(i, LocalDateTime.now(), league);
            matchRepository.save(match);
            teamMatchRepository.save(new TeamMatch(i%2 == 0, team, match));

            Team anotherTeam = new Team(101+i, "code"+i, "another"+i);
            teamRepository.save(anotherTeam);
            teamMatchRepository.save(new TeamMatch(i%2 != 0, anotherTeam, match));
        }
    }
}