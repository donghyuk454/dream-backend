package com.dream.application.domain.match.repository;

import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
class TeamMatchRepositoryTest {

    @Autowired private TeamRepository teamRepository;

    @Autowired private MatchRepository matchRepository;
    @Autowired private TeamMatchRepository teamMatchRepository;

    @Test
    @DisplayName("Team id 와 Match id 에 매핑되는 TeamMatch 가 있으면 true 를 반환합니다.")
    void existsByTeamAndMatch() {
        //given
        Team team = new Team();
        Match match = new Match();
        teamRepository.save(team);
        matchRepository.save(match);

        TeamMatch teamMatch = new TeamMatch(true, team, match);
        teamMatchRepository.save(teamMatch);

        //when
        boolean result = teamMatchRepository.existsByTeamAndMatch(team, match);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Team id 와 Match id 에 매핑되는 TeamMatch 가 없으면 false 를 반환합니다.")
    void notExistsByTeamAndMatch() {
        //given
        Team team = new Team();
        Team invalidTeam = new Team();
        Match match = new Match();
        teamRepository.save(team);
        teamRepository.save(invalidTeam);
        matchRepository.save(match);

        TeamMatch teamMatch = new TeamMatch(true, team, match);
        teamMatchRepository.save(teamMatch);

        //when
        boolean result = teamMatchRepository.existsByTeamAndMatch(invalidTeam, match);

        //then
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("Team id 와 같은 모든 TeamMatch 를 조회합니다.")
    void findAllByTeam() {
        //given
        Team team = new Team();

        List<Match> matches = new ArrayList<>();
        int matchCount = 10;
        for (int i = 0; i < matchCount; i++) {
            matches.add(new Match());
        }

        teamRepository.save(team);
        matchRepository.saveAll(matches);

        for (int i = 0; i < matchCount; i++) {
            TeamMatch teamMatch = new TeamMatch(i % 2 == 0, team, matches.get(i));
            teamMatchRepository.save(teamMatch);
        }

        //when
        List<TeamMatch> teamMatches = teamMatchRepository.findAllByTeam(team);

        //then
        assertThat(teamMatches).hasSize(matchCount);
        teamMatches.forEach(teamMatch -> assertThat(teamMatch.getTeam()).isEqualTo(team));
        List<Match> resultMatches = teamMatches.stream().map(TeamMatch::getMatch).toList();
        matches.forEach(match -> assertThat(resultMatches).contains(match));
    }
}