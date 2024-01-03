package com.dream.application.batch.team.job.step.writer;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.league.repository.LeagueRepository;
import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.team.entity.TeamLeague;
import com.dream.application.domain.team.repository.TeamLeagueRepository;
import com.dream.application.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@RequiredArgsConstructor
public class TeamWriter implements ItemWriter<Team> {

    private final TeamRepository teamRepository;
    private final TeamLeagueRepository teamLeagueRepository;

    private final League league;

    public TeamWriter(LeagueRepository leagueRepository,
                      TeamRepository teamRepository,
                      TeamLeagueRepository teamLeagueRepository,
                      Integer leagueFbaId) {
        league = leagueRepository.findByFbaId(leagueFbaId);
        this.teamRepository = teamRepository;
        this.teamLeagueRepository = teamLeagueRepository;
    }

    @Override
    public void write(List<? extends Team> items) {
        items.forEach(team -> {
            teamRepository.save(team);
            TeamLeague teamLeague = new TeamLeague(team, league);
            teamLeagueRepository.save(teamLeague);
        });
    }
}
