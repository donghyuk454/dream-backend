package com.dream.application.batch.match.job.step.reader;

import com.dream.application.batch.match.job.dto.ItemBuffer;
import com.dream.application.batch.match.job.dto.MatchApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchReader implements ItemReader<MatchApiResponse> {
    private final ItemBuffer<MatchApiResponse> itemBuffer;
    @Override
    public MatchApiResponse read() {
        if(itemBuffer.isEmpty())
            return null;

        return itemBuffer.poll();
    }
}
