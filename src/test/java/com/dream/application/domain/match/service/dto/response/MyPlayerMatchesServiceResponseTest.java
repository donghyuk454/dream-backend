package com.dream.application.domain.match.service.dto.response;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.team.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyPlayerMatchesServiceResponseTest {

    @Test
    @DisplayName("팩토리 메서드를 생성자로 사용합니다.")
    void factoryMethod() {
        //given
        Long playerId = 1L;

        League league = new League(0, "Premier League", "type", "logo.png", "England");

        Team home = new Team(1, "code1", "homeName");
        Team away = new Team(2, "code2", "awayName");
        Team myTeam = new Team(3, "code3", "myName");

        Match match1 = new Match(3, java.time.LocalDateTime.now(), league);
        Match match2 = new Match(4, java.time.LocalDateTime.now(), league);

        TeamMatch homeMatch1 = new TeamMatch(true, myTeam, match1);
        TeamMatch awayMatch1 = new TeamMatch(false, away, match1);
        TeamMatch homeMatch2 = new TeamMatch(true, home, match2);
        TeamMatch awayMatch2 = new TeamMatch(false, myTeam, match2);

        match1.addTeamMatch(homeMatch1);
        match1.addTeamMatch(awayMatch1);
        match2.addTeamMatch(homeMatch2);
        match2.addTeamMatch(awayMatch2);

        //when
        MyPlayerMatchesServiceResponse result = MyPlayerMatchesServiceResponse.of(playerId, List.of(match1, match2));

        //then
        assertThat(result.getPlayerId()).isEqualTo(playerId);
        assertThat(result.getMatches()).hasSize(2);
    }
}