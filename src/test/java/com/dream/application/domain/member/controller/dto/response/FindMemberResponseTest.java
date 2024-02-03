package com.dream.application.domain.member.controller.dto.response;

import com.dream.application.domain.member.service.dto.response.FindMemberServiceResponse;
import com.dream.application.domain.member.service.dto.response.PlayerInfoDto;
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
        PlayerInfoDto player1 = new PlayerInfoDto(1L, "손흥민");
        PlayerInfoDto player2 = new PlayerInfoDto(2L, "김민재");

        List<PlayerInfoDto> players = List.of(player1, player2);
        FindMemberServiceResponse serviceResponse = new FindMemberServiceResponse(memberId, memberName, players);

        //when
        FindMemberResponse.of(serviceResponse);

        //then
        assertThat(serviceResponse.getMemberId()).isEqualTo(memberId);
        assertThat(serviceResponse.getMemberName()).isEqualTo(memberName);
        assertThat(serviceResponse.getPlayersInfo()).hasSize(2);
        assertThat(serviceResponse.getPlayersInfo()).contains(player1, player2);
    }

}