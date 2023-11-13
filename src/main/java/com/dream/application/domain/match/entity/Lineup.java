package com.dream.application.domain.match.entity;

import com.dream.application.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "LINEUP")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lineup extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINEUP_ID")
    private Long lineupId;

    @Column(name = "FORMATION")
    private String formation;
}
