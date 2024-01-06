package com.dream.application.domain.league.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BROADCAST")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Broadcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BROADCAST_ID")
    private Long broadcastId;

    @Column(name = "channel")
    private String channel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;
}
