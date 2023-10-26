package com.dream.application.domain.player.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class PlayerDetails {
    private final String name;
    private final int number;
}