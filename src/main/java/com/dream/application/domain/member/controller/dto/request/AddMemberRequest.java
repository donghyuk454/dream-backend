package com.dream.application.domain.member.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class AddMemberRequest {
    @Email
    final String email;
    @NotBlank
    final String name;
}
