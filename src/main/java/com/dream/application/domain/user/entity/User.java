package com.dream.application.domain.user.entity;
import com.dream.application.domain.player.entity.Player;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToMany
    @JoinTable(name = "user_subscribe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscribe_id"))
    private Set<Player> subscribedPlayers;
}
