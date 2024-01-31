package com.dream.application.domain.member.repository;

import com.dream.application.domain.member.entity.Member;
import com.dream.application.domain.player.entity.Player;
import com.dream.application.domain.player.entity.PlayerDetails;
import com.dream.application.domain.player.repository.PlayerRepository;
import com.dream.application.domain.subscribe.entity.MemberSubscription;
import com.dream.application.domain.subscribe.entity.Subscription;
import com.dream.application.domain.subscribe.repository.MemberSubscriptionRepository;
import com.dream.application.domain.subscribe.repository.SubscriptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private MemberSubscriptionRepository memberSubscriptionRepository;

    @Autowired
    private EntityManager em;

    // 쿼리 횟수 확인
    // 개선 : 7(1+2+2+2)회 -> 1회
    @Test
    @DisplayName("fetch join 을 사용해 MemberSubscription 도 함께 조회합니다.")
    void findWithMemberSubscriptionsById() {
        //given
        Player player1 = new Player(new PlayerDetails("손흥민", 7));
        Player player2 = new Player(new PlayerDetails("김민재", 3));
        playerRepository.save(player1);
        playerRepository.save(player2);

        Subscription subscription1 = new Subscription(player1);
        Subscription subscription2 = new Subscription(player2);
        subscriptionRepository.save(subscription1);
        subscriptionRepository.save(subscription2);

        String memberName = "name";
        Member member = new Member("name");
        memberRepository.save(member);

        MemberSubscription memberSubscription1 = new MemberSubscription(member, subscription1);
        MemberSubscription memberSubscription2 = new MemberSubscription(member, subscription2);
        memberSubscriptionRepository.save(memberSubscription1);
        memberSubscriptionRepository.save(memberSubscription2);

        member.subscribe(memberSubscription1);
        member.subscribe(memberSubscription2);

        em.flush();
        em.clear();

        //when
        Optional<Member> result = memberRepository.findWithMemberSubscriptionsById(member.getMemberId());

        //then
        assertThat(result).isPresent();
        Member searchedMember = result.get();
        assertThat(searchedMember.getMemberName()).isEqualTo(memberName);
        List<MemberSubscription> memberSubscriptions = searchedMember.getMemberSubscriptions();
        assertThat(memberSubscriptions).hasSize(2);
        List<String> playerNames = getPlayerNameList(memberSubscriptions);
        assertThat(playerNames)
                .hasSize(2)
                .contains("김민재")
                .contains("손흥민");
    }

    private static List<String> getPlayerNameList(List<MemberSubscription> memberSubscriptions) {
        return memberSubscriptions
                .stream()
                .map(ms -> ms.getSubscription().getPlayer().getPlayerDetails().getName())
                .toList();
    }
}