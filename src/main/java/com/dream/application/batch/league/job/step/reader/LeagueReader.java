package com.dream.application.batch.league.job.step.reader;

import com.dream.application.batch.league.job.dto.response.LeagueApiResponse;
import com.dream.application.common.util.batch.api.ItemBuffer;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

@AllArgsConstructor
public class LeagueReader implements ItemReader<LeagueApiResponse> {

    private final ItemBuffer<LeagueApiResponse> itemBuffer;
    @Override
    public LeagueApiResponse read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return itemBuffer.poll();
    }
}
