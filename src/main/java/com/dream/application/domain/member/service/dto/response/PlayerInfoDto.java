package com.dream.application.domain.member.service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerInfoDto {
    private final Long playerId;
    private final String playerName;
}
