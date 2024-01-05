package com.dream.application.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class FootballEntity extends BaseEntity {
    // football api 를 통해 얻은 id
    // api 호출 시 해당 id 사용할 것
    @Column(name = "FBA_ID", unique = true)
    protected Integer fbaId;
}