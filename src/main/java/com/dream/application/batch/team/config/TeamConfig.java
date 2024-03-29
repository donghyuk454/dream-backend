package com.dream.application.batch.team.config;

import com.dream.application.batch.team.job.dto.response.TeamApiResponse;
import com.dream.application.batch.team.job.step.processor.TeamProcessor;
import com.dream.application.batch.team.job.step.reader.TeamReader;
import com.dream.application.batch.team.job.step.tasklet.TeamApiTasklet;
import com.dream.application.batch.team.job.step.writer.TeamWriter;
import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.api.dto.FootballApiInfo;
import com.dream.application.domain.league.repository.LeagueRepository;
import com.dream.application.domain.team.entity.Team;
import com.dream.application.domain.team.repository.TeamLeagueRepository;
import com.dream.application.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Profile("batch-team")
@Configuration
@RequiredArgsConstructor
public class TeamConfig {

    private static final String LEAGUE_ID = "39";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FootballApiInfo footballApiInfo;

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final TeamLeagueRepository teamLeagueRepository;

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public ItemBuffer<TeamApiResponse> teamApiResponseItemBuffer() {
        return new ItemBuffer<>();
    }

    @Bean
    @Qualifier("teamApiJob")
    public Job matchJob(){
        return jobBuilderFactory.get("teamJob")
                .preventRestart()
                .start(callTeamApiStep())
                .next(teamInitStep())
                .build();
    }

    @JobScope
    @Bean
    public Step callTeamApiStep(){
        return stepBuilderFactory.get("readTasks")
                .tasklet(teamApiTasklet())
                .build();
    }

    @JobScope
    @Bean
    public Step teamInitStep(){
        return stepBuilderFactory.get("teamStep")
                .<TeamApiResponse, Team>chunk(1)
                .reader(teamReader())
                .processor(teamProcessor())
                .writer(teamItemWriter())
                .transactionManager(transactionManager(entityManagerFactory))
                .build();
    }

    @StepScope
    @Bean
    public TeamApiTasklet teamApiTasklet() {
        return new TeamApiTasklet(teamApiResponseItemBuffer(), footballApiInfo, LEAGUE_ID);
    }

    @StepScope
    @Bean
    public ItemReader<TeamApiResponse> teamReader() {
        return new TeamReader(teamApiResponseItemBuffer());
    }

    @StepScope
    @Bean
    public ItemProcessor<TeamApiResponse, Team> teamProcessor() {
        return new TeamProcessor();
    }

    @StepScope
    @Bean
    public ItemWriter<Team> teamItemWriter() {
        return new TeamWriter(teamRepository, teamLeagueRepository, leagueRepository, Integer.parseInt(LEAGUE_ID));
    }
}
