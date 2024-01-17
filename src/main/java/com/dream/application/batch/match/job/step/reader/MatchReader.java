package com.dream.application.batch.match.job.step.reader;

import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.batch.match.job.dto.response.MatchApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

@Slf4j
@RequiredArgsConstructor
public class MatchReader implements ItemReader<MatchApiResponse> {

    private final ItemBuffer<MatchApiResponse> itemBuffer;

    @Override
    public MatchApiResponse read() {
        return itemBuffer.poll();
    }
}
