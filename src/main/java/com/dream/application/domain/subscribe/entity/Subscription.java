package com.dream.application.domain.subscribe.entity;

import com.dream.application.common.entity.BaseEntity;
import com.dream.application.domain.player.entity.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SUBSCRIPTION")
@Getter
@NoArgsConstructor
public class Subscription extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBSCRIPTION_ID")
    private Long subscriptionId;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberSubscription> userSubscriptions;

    @OneToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    public Subscription(Player player) {
        this.player = player;
    }
}