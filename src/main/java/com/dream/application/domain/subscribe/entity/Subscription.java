package com.dream.application.domain.subscribe.entity;

import com.dream.application.domain.player.entity.Player;
import com.dream.application.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
public class Subscription extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribeId;

    @ManyToMany
    @JoinTable(name = "user_subscribe",
            joinColumns = @JoinColumn(name = "subscribe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> subscribers;

    @ManyToMany
    @JoinTable(name = "player_subscribe",
            joinColumns = @JoinColumn(name = "subscribe_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> subscribedPlayers;
}

