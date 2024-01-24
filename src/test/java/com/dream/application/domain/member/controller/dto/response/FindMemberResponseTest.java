package com.dream.application.domain.member.controller.dto.response;

import com.dream.application.domain.member.service.dto.response.FindMemberServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FindMemberResponseTest {

    @Test
    @DisplayName("정적 팩토리 매서드(of)를 사용해 FindMemberResponse 를 생성합니다.")
    void factoryMethod() {
        //given
        Long memberId = 1L;
        String memberName = "name";
        List<String> players = List.of("손흥민", "김민재");
        FindMemberServiceResponse serviceResponse = new FindMemberServiceResponse(memberId, memberName, players);

        //when
        FindMemberResponse.of(serviceResponse);

        //then
        assertThat(serviceResponse.getMemberId()).isEqualTo(memberId);
        assertThat(serviceResponse.getMemberName()).isEqualTo(memberName);
        assertThat(serviceResponse.getPlayers()).hasSize(2);
        assertThat(serviceResponse.getPlayers()).contains("손흥민", "김민재");
    }

}