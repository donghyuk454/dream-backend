package com.dream.application.batch.league.job.dto.response;

import com.dream.application.batch.league.job.dto.response.detail.CountryDto;
import com.dream.application.batch.league.job.dto.response.detail.LeagueDto;
import lombok.Data;

@Data
public class LeagueApiResponse {
    private LeagueDto league;
    private CountryDto country;
}
