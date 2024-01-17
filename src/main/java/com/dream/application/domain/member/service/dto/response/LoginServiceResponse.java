package com.dream.application.domain.member.service.dto.response;

import com.dream.application.domain.member.dto.SessionMember;
import com.dream.application.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginServiceResponse {
    private SessionMember sessionMember;

    public static LoginServiceResponse of(Member member) {
        return new LoginServiceResponse(new SessionMember(member.getMemberId(), member.getMemberName()));
    }
}
