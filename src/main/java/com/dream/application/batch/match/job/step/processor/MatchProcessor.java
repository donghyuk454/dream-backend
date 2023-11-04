package com.dream.application.batch.match.job.step.processor;

import com.dream.application.domain.match.entity.Match;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MatchProcessor implements ItemProcessor<String, Match> {

    @Override
    public Match process(String item) throws Exception {
        return null;
    }
}
