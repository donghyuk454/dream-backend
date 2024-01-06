package com.dream.application.batch.match.job.dto.response.detail;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.match.entity.Match;
import lombok.Data;

import java.time.*;

@Data
public class FixtureDto {
    private Integer id;
    private String referee;
    private String timezone;
    private String date;
    private Long timestamp;

    private static final String TIMEZONE_KOREA = "Asia/Seoul";

    public Match toMatch(League league) {
        return new Match(id, getSchedule(), league);
    }

    private LocalDateTime getSchedule() {
        // timezone 가져오기
        ZoneId koreaZoneId = ZoneId.of(TIMEZONE_KOREA);
        ZoneId remoteZoneId = ZoneId.of(timezone);

        // 현재 timestamp 를 instant 로 전환
        Instant remoteInstant = Instant.ofEpochMilli(timestamp);

        // 해당 instant 와 zone id 로 ZoneDateTime 생성
        ZonedDateTime remoteDateTime = ZonedDateTime.ofInstant(remoteInstant, remoteZoneId);

        // zone 을 한국 기준으로 변경
        ZonedDateTime koreaZoneDateTime = remoteDateTime.withZoneSameInstant(koreaZoneId);

        return koreaZoneDateTime.toLocalDateTime();
    }
}
