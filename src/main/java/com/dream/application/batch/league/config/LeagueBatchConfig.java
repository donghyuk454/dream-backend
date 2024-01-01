package com.dream.application.batch.league.config;

import com.dream.application.batch.league.job.dto.response.LeagueApiResponse;
import com.dream.application.batch.league.job.step.processor.LeagueProcessor;
import com.dream.application.batch.league.job.step.reader.LeagueReader;
import com.dream.application.batch.league.job.step.tasklet.LeagueApiTasklet;
import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.api.dto.FootballApiInfo;
import com.dream.application.domain.league.entity.League;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class LeagueBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FootballApiInfo footballApiInfo;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 1;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public ItemBuffer<LeagueApiResponse> leagueApiResponseItemBuffer() {
        return new ItemBuffer<>();
    }

    @Bean
    @Qualifier("leagueApiJob")
    public Job leagueApiJob() {
        return jobBuilderFactory.get("leagueApiJob")
                .start(leagueApiStep())
                .next(leagueInitStep())
                .build();
    }

    @JobScope
    @Bean
    public Step leagueApiStep() {
        return stepBuilderFactory.get("leagueApiStep")
                .tasklet(leagueApiTasklet())
                .build();
    }

    @JobScope
    @Bean
    public Step leagueInitStep() {
        return stepBuilderFactory.get("leagueInitStep")
                .<LeagueApiResponse, League>chunk(CHUNK_SIZE)
                .reader(leagueReader())
                .processor(leagueProcessor())
                .writer(leagueJpaItemWriter())
                .transactionManager(transactionManager(entityManagerFactory))
                .build();
    }

    @StepScope
    @Bean
    public Tasklet leagueApiTasklet() {
        return new LeagueApiTasklet(leagueApiResponseItemBuffer(), footballApiInfo);
    }

    @StepScope
    @Bean
    public ItemReader<LeagueApiResponse> leagueReader() {
        return new LeagueReader(leagueApiResponseItemBuffer());
    }

    @StepScope
    @Bean
    public ItemProcessor<LeagueApiResponse, League> leagueProcessor() {
        return new LeagueProcessor();
    }

    @StepScope
    @Bean
    public JpaItemWriter<League> leagueJpaItemWriter() {
        JpaItemWriter<League> leagueJpaItemWriter = new JpaItemWriter<>();
        leagueJpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return leagueJpaItemWriter;
    }
}