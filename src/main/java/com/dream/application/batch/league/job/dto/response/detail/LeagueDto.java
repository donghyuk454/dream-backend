package com.dream.application.batch.league.job.dto.response.detail;

import com.dream.application.domain.league.entity.League;
import lombok.Data;

@Data
public class LeagueDto {
    private Integer id;
    private String name;
    private String type;
    private String logo;

    public League toLeague() {
        League league = new League(null, name, type, logo);
        league.setFbaId(id);

        return league;
    }
}