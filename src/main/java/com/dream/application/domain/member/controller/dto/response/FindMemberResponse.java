package com.dream.application.domain.member.controller.dto.response;

import com.dream.application.domain.member.service.dto.response.FindMemberServiceResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindMemberResponse {
    private final Long memberId;
    private final String memberName;
    private final List<FindMemberSubscriptionResponse> subscription;

    public static FindMemberResponse of(FindMemberServiceResponse response) {
        List<FindMemberSubscriptionResponse> subscriptionResponses = response.getPlayersInfo()
                .stream()
                .map(FindMemberSubscriptionResponse::of)
                .toList();

        return new FindMemberResponse(response.getMemberId(), response.getMemberName(), subscriptionResponses);
    }
}
