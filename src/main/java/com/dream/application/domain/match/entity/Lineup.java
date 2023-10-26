package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class Lineup extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineupId;

    private String formation;
}
