package com.dream.application.batch.league.job.step.processor;

import com.dream.application.batch.league.job.dto.response.LeagueApiResponse;
import com.dream.application.batch.league.job.dto.response.detail.LeagueDto;
import com.dream.application.domain.league.entity.League;
import org.springframework.batch.item.ItemProcessor;

public class LeagueProcessor implements ItemProcessor<LeagueApiResponse, League> {
    @Override
    public League process(LeagueApiResponse item) {
        LeagueDto leagueDto = item.getLeague();

        return leagueDto.toLeague(item.getCountry().getName());
    }
}
