package com.dream.application.batch.league.job.dto;

import lombok.Data;

@Data
public class LeagueApiResponse {
    private LeagueDto league;
    private CountryDto country;
}
