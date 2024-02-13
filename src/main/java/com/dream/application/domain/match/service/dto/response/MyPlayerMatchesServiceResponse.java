package com.dream.application.domain.match.service.dto.response;

import com.dream.application.domain.match.entity.Match;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPlayerMatchesServiceResponse {
    private final Long playerId;
    private final List<MatchDto> matches;

    // 생성자로 두 타입을 받을 수 없어 팩토리 메서드를 생성합니다.
    public static MyPlayerMatchesServiceResponse of(Long playerId, List<Match> matches) {
        return new MyPlayerMatchesServiceResponse(playerId, matches.stream().map(MatchDto::new).toList());
    }
}
