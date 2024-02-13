package com.dream.application.domain.match.service.dto.response;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.team.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MatchDtoTest {

    @Test
    @DisplayName("match entity 를 통해 dto 객체를 생성합니다.")
    void constructorTest() {
        //given
        League league = new League(0, "Premier League", "type", "logo.png", "England");
        Team home = new Team(1, "code1", "homeName");
        Team away = new Team(2, "code2", "awayName");
        Match match = new Match(3, java.time.LocalDateTime.now(), league);

        TeamMatch homeMatch = new TeamMatch(true, home, match);
        TeamMatch awayMatch = new TeamMatch(false, away, match);

        match.addTeamMatch(homeMatch);
        match.addTeamMatch(awayMatch);

        //when
        MatchDto matchDto = new MatchDto(match);

        //then
        assertThat(matchDto.getMatchId()).isEqualTo(match.getId());
        assertThat(matchDto.getLeague()).isEqualTo(league.getName());
        assertThat(matchDto.getHome()).isEqualTo(home.getName());
        assertThat(matchDto.getAway()).isEqualTo(away.getName());
        assertThat(matchDto.getSchedule()).isEqualTo(match.getSchedule());
    }

}