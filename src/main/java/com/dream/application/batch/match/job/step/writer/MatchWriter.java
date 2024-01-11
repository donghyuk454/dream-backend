package com.dream.application.batch.match.job.step.writer;

import com.dream.application.batch.match.job.step.dto.MatchProcessorResponseDto;
import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.match.repository.MatchRepository;
import com.dream.application.domain.match.repository.TeamMatchRepository;
import com.dream.application.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class MatchWriter implements ItemWriter<MatchProcessorResponseDto> {

    private final MatchRepository matchRepository;
    private final TeamMatchRepository teamMatchRepository;

    @Override
    @Transactional
    public void write(List<? extends MatchProcessorResponseDto> items) {
        items.forEach(item -> {
            // match 저장
            Match match = item.getMatch();
            matchRepository.findByFbaId(match.getFbaId())
                    .ifPresentOrElse(
                            m -> m.syncMatch(match), // 존재할 경우 Sync
                            () -> matchRepository.save(match) // 존재하지 않을 경우 저장
                    );

            // team match 저장
            Set<TeamMatch> teamMatches = item.getTeamMatches();
            teamMatches.forEach(teamMatch -> addTeamMatchIfNotPresent(match, teamMatch));
        });
    }

    private void addTeamMatchIfNotPresent(Match match, TeamMatch teamMatch) {
        Team team = teamMatch.getTeam();
        boolean isPresent = teamMatchRepository.existsByTeamAndMatch(team, match);

        if (!isPresent) {
            teamMatch.setMatch(match);
            teamMatchRepository.save(teamMatch);
            log.info("TEAM_MATCH 추가. TEAM_ID : {}, MATCH_ID : {}",
                    team.getTeamId(),
                    match.getMatchId());
        }
    }
}
