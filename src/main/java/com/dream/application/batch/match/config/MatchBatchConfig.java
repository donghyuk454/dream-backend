package com.dream.application.batch.match.config;

import com.dream.application.batch.match.job.dto.response.MatchApiResponse;
import com.dream.application.batch.match.job.step.dto.MatchProcessorResponseDto;
import com.dream.application.batch.match.job.step.processor.MatchProcessor;
import com.dream.application.batch.match.job.step.reader.MatchReader;
import com.dream.application.batch.match.job.step.tasklet.MatchApiTasklet;
import com.dream.application.batch.match.job.step.writer.MatchWriter;
import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.api.dto.FootballApiInfo;
import com.dream.application.domain.league.repository.LeagueRepository;
import com.dream.application.domain.match.repository.MatchRepository;
import com.dream.application.domain.match.repository.TeamMatchRepository;
import com.dream.application.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MatchBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FootballApiInfo footballApiInfo;

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final TeamMatchRepository teamMatchRepository;

    private final EntityManagerFactory entityManagerFactory;

    private static final String LEAGUE_ID = "39";

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public ItemBuffer<MatchApiResponse> matchApiResponseItemBuffer() {
        return new ItemBuffer<>();
    }

    @Bean
    @Qualifier("matchApiJob")
    public Job matchApiJob(){
        return jobBuilderFactory.get("matchJob")
                .preventRestart()
                .start(callMatchApiStep())
                .next(matchInitStep())
                .build();
    }

    @JobScope
    @Bean
    public Step callMatchApiStep(){
        return stepBuilderFactory.get("readTasks")
                .tasklet(matchApiTasklet())
                .build();
    }

    @JobScope
    @Bean
    public Step matchInitStep(){
        return stepBuilderFactory.get("matchStep")
                .<MatchApiResponse, MatchProcessorResponseDto>chunk(1)
                .reader(matchReader())
                .processor(matchProcessor())
                .writer(matchWriter())
                .transactionManager(transactionManager(entityManagerFactory))
                .build();
    }

    @StepScope
    @Bean
    public MatchApiTasklet matchApiTasklet() {
        return new MatchApiTasklet(matchApiResponseItemBuffer(), footballApiInfo, LEAGUE_ID);
    }

    @StepScope
    @Bean
    public MatchReader matchReader() {
        return new MatchReader(matchApiResponseItemBuffer());
    }

    @StepScope
    @Bean
    public MatchProcessor matchProcessor() {
        return new MatchProcessor(leagueRepository, teamRepository);
    }

    @StepScope
    @Bean
    public MatchWriter matchWriter() {
        return new MatchWriter(matchRepository, teamMatchRepository);
    }
}
