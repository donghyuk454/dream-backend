package com.dream.application.domain.member.controller.dto.response;

import com.dream.application.domain.member.dto.SessionMember;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private SessionMember sessionMember;
}
