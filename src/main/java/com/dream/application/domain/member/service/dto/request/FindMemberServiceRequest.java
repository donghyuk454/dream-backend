package com.dream.application.domain.member.service.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindMemberServiceRequest {
    private final Long memberId;
}
