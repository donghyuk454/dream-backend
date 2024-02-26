package com.dream.application.domain.member.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddMemberRequest {
    final String email;
    final String name;
}
