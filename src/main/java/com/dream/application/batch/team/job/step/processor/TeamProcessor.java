package com.dream.application.batch.team.job.step.processor;

import com.dream.application.batch.team.job.dto.response.TeamApiResponse;
import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.league.repository.LeagueRepository;
import com.dream.application.domain.team.entity.Team;
import org.springframework.batch.item.ItemProcessor;

public class TeamProcessor implements ItemProcessor<TeamApiResponse, Team> {

    private final League league;

    public TeamProcessor(LeagueRepository leagueRepository, Integer leagueFbaId) {
        league = leagueRepository.findByFbaId(leagueFbaId);
    }

    @Override
    public Team process(TeamApiResponse item) {
        return item.getTeam().toTeam(league);
    }
}
