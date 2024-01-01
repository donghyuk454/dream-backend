package com.dream.application.batch.team.job.step.tasklet;

import com.dream.application.batch.match.job.dto.request.SeasonLeagueParameter;
import com.dream.application.batch.team.job.dto.response.TeamApiResponse;
import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.api.dto.FootballApiInfo;
import com.dream.application.common.util.batch.api.tasklet.FootballApiTasklet;

public class TeamApiTasklet extends FootballApiTasklet<SeasonLeagueParameter, TeamApiResponse> {

    private static final String SEASON = "2023";
    private static final String ENDPOINT = "/v3/teams?season=" + SEASON + "&league=";
    private static final String METHOD = "GET";


    public TeamApiTasklet(ItemBuffer<TeamApiResponse> responseBuffer,
                          FootballApiInfo footballApiInfo,
                          String league) {
        super(responseBuffer, ENDPOINT + league, METHOD, null, footballApiInfo, TeamApiResponse.class);
    }
}
