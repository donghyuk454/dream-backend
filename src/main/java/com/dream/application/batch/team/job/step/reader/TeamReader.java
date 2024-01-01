package com.dream.application.batch.team.job.step.reader;

import com.dream.application.batch.team.job.dto.response.TeamApiResponse;
import com.dream.application.common.util.batch.api.ItemBuffer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeamReader implements ItemReader<TeamApiResponse> {

    private final ItemBuffer<TeamApiResponse> itemBuffer;

    @Override
    public TeamApiResponse read() {
        return itemBuffer.poll();
    }
}
