package com.dream.application.domain.subscribe.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.player.entity.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class Subscription extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribeId;

    @OneToMany(mappedBy = "userSubscription")
    private UserSubscription userSubscription;

    @OneToOne
    private Player player;
}