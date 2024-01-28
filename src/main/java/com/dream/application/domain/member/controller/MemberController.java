package com.dream.application.domain.member.controller;

import com.dream.application.common.response.BaseResponse;
import com.dream.application.domain.member.controller.dto.response.FindMemberResponse;
import com.dream.application.domain.member.service.MemberService;
import com.dream.application.domain.member.service.dto.request.FindMemberServiceRequest;
import com.dream.application.domain.member.service.dto.response.FindMemberServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public BaseResponse<FindMemberResponse> getMemberDetailInfo(@PathVariable(name = "memberId") Long memberId) {
        FindMemberServiceResponse serviceResponse =
                memberService.findMemberWithSubscription(new FindMemberServiceRequest(memberId));

        return BaseResponse.success(FindMemberResponse.of(serviceResponse));
    }
}
