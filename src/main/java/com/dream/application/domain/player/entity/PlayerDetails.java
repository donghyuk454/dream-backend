package com.dream.application.domain.player.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDetails {
    @Column(name = "NAME")
    private String name;
    @Column(name = "NUMBER")
    private int number;
}