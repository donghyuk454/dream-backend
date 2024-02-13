package com.dream.application.batch.team.job.step.writer;

import com.dream.application.domain.league.entity.League;
import com.dream.application.domain.league.repository.LeagueRepository;
import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.team.entity.TeamLeague;
import com.dream.application.domain.team.repository.TeamLeagueRepository;
import com.dream.application.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
public class TeamWriter implements ItemWriter<Team> {

    private final TeamRepository teamRepository;
    private final TeamLeagueRepository teamLeagueRepository;
    private final LeagueRepository leagueRepository;

    private final Integer leagueFbaId;

    private static final String NO_LEAGUE_MESSAGE = "존재하지 않는 LEAGUE 입니다. FBA_ID : ";

    @Override
    public void write(List<? extends Team> items) throws NoSuchElementException {
        League league = leagueRepository.findByFbaId(leagueFbaId)
                .orElseThrow(() -> new NoSuchElementException( NO_LEAGUE_MESSAGE + leagueFbaId ));

        items.forEach(team -> {
            saveTeamIfNotPresent(team);

            TeamLeague teamLeague = new TeamLeague(team, league);

            saveTeamLeagueIfNotPresent(teamLeague);
        });
    }

    private void saveTeamLeagueIfNotPresent(TeamLeague teamLeague) {
        boolean isPresent = teamLeagueRepository.existsByTeamAndLeague(teamLeague.getTeam(), teamLeague.getLeague());

        if (isPresent) return;

        teamLeagueRepository.save(teamLeague);

        log.info("신규 생성된 TEAM_LEAGUE 입니다. TEAM_ID : {}, LEAGUE_ID : {}",
                teamLeague.getTeam().getId(),
                teamLeague.getLeague().getLeagueId());
    }

    private void saveTeamIfNotPresent(Team team) {
        boolean isPresent = teamRepository.existsByFbaId(team.getFbaId());

        if(isPresent) return;

        teamRepository.save(team);
        log.info("신규 생성된 TEAM 입니다. FBA_ID : {}", team.getFbaId());
    }
}
