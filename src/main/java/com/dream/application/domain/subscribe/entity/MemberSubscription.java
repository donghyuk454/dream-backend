package com.dream.application.domain.subscribe.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER_SUBSCRIPTION")
@Getter
@NoArgsConstructor
public class MemberSubscription extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SUBSCRIPTION_ID")
    private Long memberSubscriptionId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSCRIPTION_ID")
    private Subscription subscription;

    @Builder
    public MemberSubscription(Member member, Subscription subscription) {
        this.member = member;
        this.subscription = subscription;
    }
}
