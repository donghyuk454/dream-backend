package com.dream.application.batch.team.job.dto.response.detail;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.team.entity.Team;
import lombok.Data;

@Data
public class TeamDto {
    private Integer id;
    private String name;
    private String code;
    private String country;
    private String logo;

    public Team toTeam() {
        return new Team(id, code, name);
    }
}
