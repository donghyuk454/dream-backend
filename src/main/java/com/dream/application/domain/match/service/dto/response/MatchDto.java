package com.dream.application.domain.match.service.dto.response;

import com.dream.application.domain.match.entity.Match;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MatchDto {
    private final Long matchId;
    private final String league;
    private final String home;
    private final String away;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime schedule;

    public MatchDto(Match match) {
        this(match.getId(),
                match.getLeague().getName(),
                match.getTeamName(true),
                match.getTeamName(false),
                match.getSchedule());
    }
}
