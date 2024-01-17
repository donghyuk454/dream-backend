package com.dream.application.domain.member.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginServiceRequest {
    private Long memberId;
}
