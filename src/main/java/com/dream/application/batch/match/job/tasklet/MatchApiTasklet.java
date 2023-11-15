package com.dream.application.batch.match.job.tasklet;

import com.dream.application.batch.match.job.dto.ItemBuffer;
import com.dream.application.batch.match.job.dto.MatchApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchApiTasklet implements Tasklet {

    private final ItemBuffer<MatchApiResponse> matchApiResponseItemBuffer;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        log.info("hihi");
        log.info(contribution.toString());
        log.info(chunkContext.toString());
        return RepeatStatus.FINISHED;
    }
}
