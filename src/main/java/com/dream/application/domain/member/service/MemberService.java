package com.dream.application.domain.member.service;

import com.dream.application.common.exception.DreamException;
import com.dream.application.domain.member.entity.Member;
import com.dream.application.domain.member.repository.MemberRepository;
import com.dream.application.domain.member.service.dto.request.FindMemberServiceRequest;
import com.dream.application.domain.member.service.dto.response.FindMemberServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public FindMemberServiceResponse findMemberWithSubscription(FindMemberServiceRequest request) {
        Long memberId = request.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DreamException("member.notfound.error"));

        return FindMemberServiceResponse.of(member);
    }
}
