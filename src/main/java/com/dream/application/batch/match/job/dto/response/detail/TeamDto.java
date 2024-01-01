package com.dream.application.batch.match.job.dto.response.detail;

import lombok.Data;

@Data
public class TeamDto {
    private Integer id;
    private String name;
    private String logo;
    private Boolean winner;
}
