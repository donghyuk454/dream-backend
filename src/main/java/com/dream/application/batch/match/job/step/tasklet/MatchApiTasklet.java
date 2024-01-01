package com.dream.application.batch.match.job.step.tasklet;

import com.dream.application.batch.match.job.dto.request.SeasonLeagueParameter;
import com.dream.application.batch.match.job.dto.response.MatchApiResponse;
import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.api.dto.FootballApiInfo;
import com.dream.application.common.util.batch.api.tasklet.FootballApiTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MatchApiTasklet extends FootballApiTasklet<SeasonLeagueParameter, MatchApiResponse> {

    private static final String SEASON = "2023";
    private static final String ENDPOINT = "/v3/fixtures?season=" + SEASON + "&league=";
    private static final String METHOD = "GET";

    public MatchApiTasklet(ItemBuffer<MatchApiResponse> responseBuffer,
                           FootballApiInfo footballApiInfo,
                           String league) {
        super(responseBuffer, ENDPOINT+league , METHOD, null, footballApiInfo, MatchApiResponse.class);
    }
}
