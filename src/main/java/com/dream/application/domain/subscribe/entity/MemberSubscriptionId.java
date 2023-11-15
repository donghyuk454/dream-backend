package com.dream.application.domain.subscribe.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberSubscriptionId implements Serializable {
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "SUBSCRIPTION_ID")
    private Long subscriptionId;
}
