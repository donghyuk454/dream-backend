package com.dream.application.domain.match.service.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyPlayerMatchesServiceRequest {
    private final Long playerId;
    private final Integer season;
}
