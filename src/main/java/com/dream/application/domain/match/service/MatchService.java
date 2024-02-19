package com.dream.application.domain.match.service;

import com.dream.application.common.exception.DreamException;
import com.dream.application.domain.match.entity.TeamMatch;
import com.dream.application.domain.match.repository.TeamMatchRepository;
import com.dream.application.domain.match.service.dto.request.MyPlayerMatchesServiceRequest;
import com.dream.application.domain.match.service.dto.response.MyPlayerMatchesServiceResponse;
import com.dream.application.domain.player.entity.Player;
import com.dream.application.domain.player.repository.PlayerRepository;
import com.dream.application.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {

    private final TeamMatchRepository teamMatchRepository;
    private final PlayerRepository playerRepository;

    public MyPlayerMatchesServiceResponse getMyPlayerMatches(MyPlayerMatchesServiceRequest request) {
        Long playerId = request.getPlayerId();
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new DreamException("player.notfound.error"));

        Team team = player.getTeam();

        List<TeamMatch> teamMatches = teamMatchRepository.findAllByTeam(team);

        return MyPlayerMatchesServiceResponse.of(playerId,
                                                teamMatches.stream()
                                                        .map(TeamMatch::getMatch)
                                                        .toList());
    }
}
