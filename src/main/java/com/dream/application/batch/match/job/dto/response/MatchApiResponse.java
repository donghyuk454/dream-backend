package com.dream.application.batch.match.job.dto.response;

import com.dream.application.batch.match.job.dto.response.detail.FixtureDto;
import com.dream.application.batch.match.job.dto.response.detail.GoalDto;
import com.dream.application.batch.match.job.dto.response.detail.LeagueDto;
import com.dream.application.batch.match.job.dto.response.detail.TeamsDto;
import lombok.Data;

@Data
public class MatchApiResponse {
    private FixtureDto fixture;
    private LeagueDto league;
    private TeamsDto teams;
    private GoalDto goals;
}
