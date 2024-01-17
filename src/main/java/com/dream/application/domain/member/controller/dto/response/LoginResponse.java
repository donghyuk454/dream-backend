package com.dream.application.domain.member.controller.dto.response;

import com.dream.application.domain.member.dto.SessionMember;
import com.dream.application.domain.member.service.dto.response.LoginServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private SessionMember sessionMember;

    public static LoginResponse of(LoginServiceResponse loginServiceResponse) {
        return new LoginResponse(loginServiceResponse.getSessionMember());
    }
}
