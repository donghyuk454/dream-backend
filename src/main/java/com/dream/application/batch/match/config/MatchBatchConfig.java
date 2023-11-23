package com.dream.application.batch.match.config;

import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.batch.match.job.dto.MatchApiResponse;
import com.dream.application.batch.match.job.step.processor.MatchProcessor;
import com.dream.application.batch.match.job.step.reader.MatchReader;
import com.dream.application.batch.match.job.step.writer.MatchWriter;
import com.dream.application.batch.match.job.step.tasklet.MatchApiTasklet;
import com.dream.application.domain.match.entity.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MatchBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final MatchReader matchReader;
    private final MatchProcessor matchProcessor;
    private final MatchWriter matchWriter;
    private final MatchApiTasklet matchTasklet;

    @Bean
    public Job matchJob(){
        log.info("job init");
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
                .tasklet(matchTasklet)
                .build();
    }

    @JobScope
    @Bean
    public Step matchInitStep(){
        log.info("step init");
        return stepBuilderFactory.get("matchStep")
                .<MatchApiResponse, Match>chunk(1)
                .reader(matchReader)
                .processor(matchProcessor)
                .writer(matchWriter)
                .build();
    }

    @Bean
    public ItemBuffer<MatchApiResponse> matchApiResponseItemBuffer() {
        return new ItemBuffer<>();
    }
}
