package com.dream.application.domain.member.service;

import com.dream.application.common.exception.DreamException;
import com.dream.application.domain.member.entity.Member;
import com.dream.application.domain.member.repository.AuthMemberRepository;
import com.dream.application.domain.member.repository.MemberRepository;
import com.dream.application.domain.member.service.dto.request.LoginServiceRequest;
import com.dream.application.domain.member.service.dto.response.LoginServiceResponse;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthMemberRepository authMemberRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("member id 로 로그인하여 성공하면 정상 SessionMember return")
    void login() {
        //given
        String userName = "memberName";
        Member member = new Member(userName);
        member = memberRepository.save(member);
        Long memberId = member.getMemberId();

        //when
        LoginServiceResponse response = authService.login(new LoginServiceRequest(memberId));

        //then
        assertThat(response.getSessionMember().getUsername()).isEqualTo(userName);
        assertThat(response.getSessionMember().getMemberId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("member id 로 로그인하여 실패하면 throw DreamException")
    void invalidLoginThenThrowException() {
        //given
        String userName = "memberName";
        Member member = new Member(userName);
        member = memberRepository.save(member);
        Long memberId = member.getMemberId() + 1;

        //when
        //then
        assertThrows(DreamException.class,
                ()->authService.login(new LoginServiceRequest(memberId)),
                "member.notfound.error");
    }

}