package com.dream.application.batch.match.config;

import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.batch.match.job.dto.response.MatchApiResponse;
import com.dream.application.batch.match.job.step.processor.MatchProcessor;
import com.dream.application.batch.match.job.step.reader.MatchReader;
import com.dream.application.batch.match.job.step.writer.MatchWriter;
import com.dream.application.batch.match.job.step.tasklet.MatchApiTasklet;
import com.dream.application.common.util.batch.api.dto.FootballApiInfo;
import com.dream.application.domain.match.entity.Match;
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

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MatchBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FootballApiInfo footballApiInfo;

    private static final String LEAGUE_ID = "342";

    @Bean
    public ItemBuffer<MatchApiResponse> matchApiResponseItemBuffer() {
        return new ItemBuffer<>();
    }

    @Bean
    @Qualifier("matchApiJob")
    public Job matchJob(){
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
                .<MatchApiResponse, Match>chunk(1)
                .reader(matchReader())
                .processor(matchProcessor())
                .writer(matchWriter())
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
        return new MatchProcessor();
    }

    @StepScope
    @Bean
    public MatchWriter matchWriter() {
        return new MatchWriter();
    }
}
