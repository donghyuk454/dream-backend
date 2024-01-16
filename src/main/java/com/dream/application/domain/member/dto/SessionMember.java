package com.dream.application.domain.member.dto;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private String memberId;
    private String username;

    @Serial
    private static final long serialVersionUID = 1L;
}
