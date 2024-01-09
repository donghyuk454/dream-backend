package com.dream.application.batch.match.job.step.writer;

import com.dream.application.batch.match.job.step.dto.MatchProcessorResponseDto;
import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.match.repository.MatchRepository;
import com.dream.application.domain.match.repository.TeamMatchRepository;
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
            Match match = matchRepository.save(item.getMatch());

            // team match 저장
            Set<TeamMatch> teamMatches = item.getTeamMatches();
            teamMatches.forEach(teamMatch -> {
                teamMatch.setMatch(match);
                teamMatchRepository.save(teamMatch);
            });
        });
    }
}
