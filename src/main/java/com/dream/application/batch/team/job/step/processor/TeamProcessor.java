package com.dream.application.batch.team.job.step.processor;

import com.dream.application.batch.team.job.dto.response.TeamApiResponse;
import com.dream.application.domain.team.entity.Team;
import org.springframework.batch.item.ItemProcessor;

public class TeamProcessor implements ItemProcessor<TeamApiResponse, Team> {

    @Override
    public Team process(TeamApiResponse item) {
        return item.getTeam().toTeam();
    }
}
