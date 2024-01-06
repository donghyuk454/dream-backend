package com.dream.application.batch.match.job.step.dto;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.match.entity.Match;
import com.dream.application.domain.match.entity.TeamMatch;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class MatchProcessorResponseDto {

    private final Match match;
    private final League league;
    private final Set<TeamMatch> teamMatches;

    @Builder
    public MatchProcessorResponseDto(Match match, League league, Set<TeamMatch> teamMatches) {
        this.match = match;
        this.league = league;
        this.teamMatches = teamMatches;
    }
}
