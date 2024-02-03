package com.dream.application.domain.member.service.dto.response;

import com.dream.application.domain.member.entity.Member;
import com.dream.application.domain.player.entity.Player;
import com.dream.application.domain.subscribe.entity.MemberSubscription;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindMemberServiceResponse {
    private final Long memberId;
    private final String memberName;
    private final List<PlayerInfoDto> playersInfo;

    public static FindMemberServiceResponse of(Member member) {
        return new FindMemberServiceResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberSubscriptions()
                        .stream()
                        .map(FindMemberServiceResponse::getPlayerInfo)
                        .toList()
        );
    }

    private static PlayerInfoDto getPlayerInfo(MemberSubscription subscription) {
        Player player = subscription.getSubscription().getPlayer();
        return new PlayerInfoDto(player.getPlayerId(), player.getPlayerDetails().getName());
    }
}
