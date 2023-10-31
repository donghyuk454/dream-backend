package com.dream.application.domain.player.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDetails {
    @Column(name = "NAME")
    private String name;
    @Column(name = "NUMBER")
    private int number;
}