package com.dream.application.batch.match.job.step.processor;

import com.dream.application.batch.match.job.dto.response.MatchApiResponse;
import com.dream.application.batch.match.job.dto.response.detail.TeamDto;
import com.dream.application.batch.match.job.step.dto.MatchProcessorResponseDto;
import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.league.repository.LeagueRepository;
import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchProcessor implements ItemProcessor<MatchApiResponse, MatchProcessorResponseDto> {

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;

    @Override
    public MatchProcessorResponseDto process(MatchApiResponse item) {
        // league 조회
        League league = leagueRepository.findByFbaId(item.getLeague().getId());

        // match 생성
        Match match = item.getFixture().toMatch(league);

        // team 조회
        TeamDto homeTeamDto = item.getTeams().getHome();
        TeamDto awayTeamDto = item.getTeams().getAway();

        Team homeTeam = getTeam(homeTeamDto.getId());
        Team awayTeam = getTeam(awayTeamDto.getId());

        // team match 생성 (home)
        TeamMatch homeTeamMatch = new TeamMatch(true, homeTeam, match);

        // team match 생성 (away)
        TeamMatch awayTeamMatch = new TeamMatch(false, awayTeam, match);

        return createResponse(league, match, homeTeamMatch, awayTeamMatch);
    }

    private static MatchProcessorResponseDto createResponse(League league,
                                                            Match match,
                                                            TeamMatch homeTeamMatch,
                                                            TeamMatch awayTeamMatch) {
        return MatchProcessorResponseDto.builder()
                .match(match)
                .league(league)
                .teamMatches(createTeamMatchSet(homeTeamMatch, awayTeamMatch))
                .build();
    }

    private Team getTeam(Integer id) {
        return teamRepository.findByFbaId(id);
    }

    public static Set<TeamMatch> createTeamMatchSet(TeamMatch ... teamMatches) {
        return new HashSet<>(Arrays.stream(teamMatches).toList());
    }
}
