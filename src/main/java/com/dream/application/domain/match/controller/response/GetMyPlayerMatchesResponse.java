package com.dream.application.domain.match.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetMyPlayerMatchesResponse {
    private final Long playerId;
    private final Integer season;
    private final List<MatchResponse> matches;
}