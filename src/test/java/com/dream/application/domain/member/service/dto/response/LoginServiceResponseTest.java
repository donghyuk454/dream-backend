package com.dream.application.domain.member.service.dto.response;

import com.dream.application.domain.member.dto.SessionMember;
import com.dream.application.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LoginServiceResponseTest {
    @Test
    @DisplayName("LoginServiceResponse 를 정적 팩토리 매소드로 생성합니다.")
    void memberToLoginServiceResponse() {
        //given
        Long memberId = 1L;
        String name = "name";
        Member member = new Member(memberId, name, null);
        //when
        LoginServiceResponse result = LoginServiceResponse.of(member);
        //then
        SessionMember sessionMember = result.getSessionMember();
        assertThat(sessionMember.getMemberId()).isEqualTo(memberId);
        assertThat(sessionMember.getUsername()).isEqualTo(name);
    }
}