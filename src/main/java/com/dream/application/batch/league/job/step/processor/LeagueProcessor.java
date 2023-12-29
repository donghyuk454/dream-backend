package com.dream.application.batch.league.job.step.processor;

import com.dream.application.batch.league.job.dto.LeagueApiResponse;
import com.dream.application.batch.league.job.dto.LeagueDto;
import com.dream.application.domain.league.entity.League;
import org.springframework.batch.item.ItemProcessor;

public class LeagueProcessor implements ItemProcessor<LeagueApiResponse, League> {
    @Override
    public League process(LeagueApiResponse item) throws Exception {
        LeagueDto leagueDto = item.getLeague();

        return leagueDto.toLeague();
    }
}
