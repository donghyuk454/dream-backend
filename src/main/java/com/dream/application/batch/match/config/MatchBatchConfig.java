package com.dream.application.batch.match.config;

import com.dream.application.batch.match.job.step.processor.MatchProcessor;
import com.dream.application.batch.match.job.step.reader.MatchReader;
import com.dream.application.batch.match.job.step.writer.MatchWriter;
import com.dream.application.domain.match.entity.Match;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MatchBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final MatchReader matchReader;
    private final MatchProcessor matchProcessor;
    private final MatchWriter matchWriter;

    @Bean
    public Job matchJob(){
        return new JobBuilder("matchJob", jobRepository)
                .start(matchStep())
                .build();
    }

    @Bean
    public Step matchStep(){
        return new StepBuilder("matchStep", jobRepository)
                .<String, Match>chunk(10, transactionManager)
                .reader(matchReader)
                .processor(matchProcessor)
                .writer(matchWriter)
                .build();
    }
}
