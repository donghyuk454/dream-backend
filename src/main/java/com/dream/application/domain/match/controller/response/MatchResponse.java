package com.dream.application.domain.match.controller.response;

import com.dream.application.domain.match.service.dto.response.MatchDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MatchResponse {
    private final Long matchId;
    private final String league;
    private final String home;
    private final String away;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime schedule;

    public MatchResponse(MatchDto matchDto) {
        this(matchDto.getMatchId(), matchDto.getLeague(), matchDto.getHome(), matchDto.getAway(), matchDto.getSchedule());
    }
}
