package com.dream.application.domain.member.service;

import com.dream.application.common.exception.DreamException;
import com.dream.application.domain.member.entity.Member;
import com.dream.application.domain.member.repository.AuthMemberRepository;
import com.dream.application.domain.member.service.dto.request.LoginServiceRequest;
import com.dream.application.domain.member.service.dto.response.LoginServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMemberRepository authMemberRepository;

    public LoginServiceResponse login(LoginServiceRequest loginRequest) throws DreamException {
        Member member = authMemberRepository.findById(loginRequest.getMemberId())
                .orElseThrow(() -> new DreamException("member.notfound.error"));

        return LoginServiceResponse.of(member);
    }
}
