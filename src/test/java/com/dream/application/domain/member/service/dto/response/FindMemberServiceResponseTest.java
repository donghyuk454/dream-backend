package com.dream.application.domain.member.service.dto.response;

import com.dream.application.domain.member.entity.Member;
import com.dream.application.domain.player.entity.Player;
import com.dream.application.domain.player.entity.PlayerDetails;
import com.dream.application.domain.subscribe.entity.MemberSubscription;
import com.dream.application.domain.subscribe.entity.Subscription;
import com.dream.application.domain.team.entity.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class FindMemberServiceResponseTest {

    @Test
    @DisplayName("정적 팩토리 매서드(of)를 사용해 FindMemberServiceResponse 를 생성합니다.")
    void factoryMethod() {
        //given
        Player player1 = new Player(new PlayerDetails("손흥민", 1), new Team());
        Player player2 = new Player(new PlayerDetails("김민재", 2), new Team());
        Subscription subscription1 = new Subscription(player1);
        Subscription subscription2 = new Subscription(player2);
        Member member = new Member(1L, "name", new ArrayList<>());
        MemberSubscription memberSubscription1 = new MemberSubscription(member, subscription1);
        MemberSubscription memberSubscription2 = new MemberSubscription(member, subscription2);
        member.subscribe(memberSubscription1);
        member.subscribe(memberSubscription2);
        //when
        FindMemberServiceResponse serviceResponse = FindMemberServiceResponse.of(member);

        //then
        assertThat(serviceResponse.getMemberId()).isEqualTo(member.getMemberId());
        assertThat(serviceResponse.getMemberName()).isEqualTo(member.getMemberName());
        assertThat(serviceResponse.getPlayersInfo()).hasSize(2);
        assertThat(serviceResponse.getPlayersInfo().stream().map(PlayerInfoDto::getPlayerName).toList())
                .contains("손흥민", "김민재");
    }

}