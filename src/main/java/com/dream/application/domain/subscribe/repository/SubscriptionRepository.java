package com.dream.application.domain.subscribe.repository;

import com.dream.application.domain.subscribe.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
