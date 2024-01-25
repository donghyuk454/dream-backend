package com.dream.application.domain.member.service.dto.response;

import com.dream.application.domain.member.entity.Member;
import com.dream.application.domain.subscribe.entity.MemberSubscription;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindMemberServiceResponse {
    private final Long memberId;
    private final String memberName;
    private final List<String> players;

    public static FindMemberServiceResponse of(Member member) {
        return new FindMemberServiceResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberSubscriptions()
                        .stream()
                        .map(FindMemberServiceResponse::getPlayerName)
                        .toList()
        );
    }

    private static String getPlayerName(MemberSubscription subscription) {
        return subscription.getSubscription()
                .getPlayer()
                .getPlayerDetails()
                .getName();
    }
}
