package com.dream.application.batch.match.job.step.writer;

import com.dream.application.domain.match.entity.Match;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class MatchWriter implements ItemWriter<Match> {

    @Override
    public void write(Chunk<? extends Match> chunk) throws Exception {

    }
}
