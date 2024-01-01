package com.dream.application.batch.league.job.step.tasklet;

import com.dream.application.batch.league.job.dto.response.LeagueApiResponse;
import com.dream.application.batch.league.job.dto.request.SeasonParameter;
import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.api.dto.FootballApiInfo;
import com.dream.application.common.util.batch.api.tasklet.FootballApiTasklet;
import lombok.NonNull;

public class LeagueApiTasklet extends FootballApiTasklet<SeasonParameter, LeagueApiResponse> {

    private static final String SEASON = "2023";
    private static final String ENDPOINT = "/v3/leagues?season=";
    private static final String METHOD = "GET";

    public LeagueApiTasklet(@NonNull ItemBuffer<LeagueApiResponse> responseBuffer,
                            FootballApiInfo footballApiInfo) {
        super(responseBuffer, ENDPOINT+SEASON , METHOD, null, footballApiInfo, LeagueApiResponse.class);
    }
}
