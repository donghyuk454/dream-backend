package com.dream.application.domain.league.repository;

import com.dream.application.domain.league.entity.League;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@DataJpaTest
@Rollback(value = false)
class LeagueRepositoryTest {

    @Autowired
    private LeagueRepository leagueRepository;

    @DisplayName("fba_id 로 조회합니다.")
    @Test
    void findByFbaId() {
        //given
        Integer fbaId = 100;
        String name = "name";
        League league = new League(fbaId, name, "type", "logo", "country");
        league = leagueRepository.save(league);

        //when
        League result = leagueRepository.findByFbaId(fbaId).get();

        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(league);
    }
}