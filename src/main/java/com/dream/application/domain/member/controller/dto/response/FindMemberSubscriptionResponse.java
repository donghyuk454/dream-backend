package com.dream.application.domain.member.controller.dto.response;

import com.dream.application.domain.member.service.dto.response.PlayerInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindMemberSubscriptionResponse {
    private final Long playerId;
    private final String playerName;

    public static FindMemberSubscriptionResponse of (PlayerInfoDto playerInfo) {
        return new FindMemberSubscriptionResponse(playerInfo.getPlayerId(), playerInfo.getPlayerName());
    }
}
