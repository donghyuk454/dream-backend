package com.dream.application.batch.league.job.dto;

import com.dream.application.domain.league.entity.League;
import lombok.Data;

@Data
public class LeagueDto {
    private Integer id;
    private String name;
    private String type;
    private String logo;

    public League toLeague() {
        return new League(id.longValue(), name, type, logo);
    }
}
