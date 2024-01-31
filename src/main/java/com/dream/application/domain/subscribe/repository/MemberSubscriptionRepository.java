package com.dream.application.domain.subscribe.repository;

import com.dream.application.domain.subscribe.entity.MemberSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSubscriptionRepository extends JpaRepository<MemberSubscription, Long> {
}
