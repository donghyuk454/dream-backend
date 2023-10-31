package com.dream.application.domain.subscribe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberSubscriptionId {
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "SUBSCRIPTION_ID")
    private Long subscriptionId;
}
