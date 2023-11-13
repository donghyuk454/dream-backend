package com.dream.application.domain.subscribe.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER_SUBSCRIPTION")
@Getter
@NoArgsConstructor
public class MemberSubscription extends BaseEntity {
    @EmbeddedId
    private MemberSubscriptionId id;

    @MapsId(value = "memberId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @MapsId(value = "subscriptionId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSCRIPTION_ID")
    private Subscription subscription;
}
