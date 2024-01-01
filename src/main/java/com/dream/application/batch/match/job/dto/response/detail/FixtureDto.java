package com.dream.application.batch.match.job.dto.response.detail;

import lombok.Data;

@Data
public class FixtureDto {
    private Long id;
    private String referee;
    private String timezone;
    private String date;
    private Long timestamp;
}
