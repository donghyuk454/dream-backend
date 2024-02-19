package com.dream.application.domain.match.controller;

import com.dream.application.common.response.BaseResponse;
import com.dream.application.domain.match.controller.request.GetMyPlayerMatchesRequest;
import com.dream.application.domain.match.controller.response.GetMyPlayerMatchesResponse;
import com.dream.application.domain.match.controller.response.MatchResponse;
import com.dream.application.domain.match.service.MatchService;
import com.dream.application.domain.match.service.dto.request.MyPlayerMatchesServiceRequest;
import com.dream.application.domain.match.service.dto.response.MyPlayerMatchesServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/players/{playerId}")
    public BaseResponse<GetMyPlayerMatchesResponse> getMyPlayerMatches(@PathVariable(name = "playerId") Long playerId,
                                                                       GetMyPlayerMatchesRequest request) {
        MyPlayerMatchesServiceResponse myPlayerMatches
                = matchService.getMyPlayerMatches(new MyPlayerMatchesServiceRequest(playerId, 2023));

        List<MatchResponse> matchResponses = myPlayerMatches
                .getMatches()
                .stream()
                .map(MatchResponse::new)
                .toList();

        return BaseResponse.success(new GetMyPlayerMatchesResponse(myPlayerMatches.getPlayerId(), 2023, matchResponses));
    }
}
