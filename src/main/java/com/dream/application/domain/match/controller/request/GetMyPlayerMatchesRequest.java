package com.dream.application.domain.match.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetMyPlayerMatchesRequest {
    private final Integer season;
}
