package com.dream.application.batch.match.job.step.processor;

import com.dream.application.batch.match.job.dto.MatchApiResponse;
import com.dream.application.domain.match.entity.Match;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MatchProcessor implements ItemProcessor<MatchApiResponse, Match> {

    @Override
    public Match process(MatchApiResponse item) {
        log.info("process start");
        log.info("item : " + item);
        return null;
    }
}
