package com.dream.application.batch.match.job.step.writer;

import com.dream.application.domain.match.entity.Match;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MatchWriter implements ItemWriter<Match> {
    @Override
    public void write(List<? extends Match> items) {
        log.info("size : " + items.size());
        log.info("finished");
    }
}
