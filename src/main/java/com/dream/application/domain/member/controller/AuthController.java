package com.dream.application.domain.member.controller;

import com.dream.application.common.response.BaseResponse;
import com.dream.application.domain.member.controller.dto.request.LoginRequest;
import com.dream.application.domain.member.controller.dto.response.LoginResponse;
import com.dream.application.domain.member.dto.SessionMember;
import com.dream.application.domain.member.service.AuthService;
import com.dream.application.domain.member.service.dto.request.LoginServiceRequest;
import com.dream.application.domain.member.service.dto.response.LoginServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.dream.application.common.session.SessionConst.LOGIN_SESSION_KEY;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest,
                                               final HttpSession session) {
        LoginServiceResponse loginResponse = authService.login(new LoginServiceRequest(loginRequest.getMemberId()));

        // session 저장
        SessionMember sessionMember = loginResponse.getSessionMember();
        session.setAttribute(LOGIN_SESSION_KEY, sessionMember);

        return BaseResponse.success(new LoginResponse(sessionMember));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(final HttpSession session) {
        session.invalidate();
        return BaseResponse.success(null);
    }
}
