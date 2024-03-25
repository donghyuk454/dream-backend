package com.dream.application.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SessionMember implements Serializable {

    private Long memberId;
    private String email;
    private String username;

    @Serial
    private static final long serialVersionUID = 1L;
}
