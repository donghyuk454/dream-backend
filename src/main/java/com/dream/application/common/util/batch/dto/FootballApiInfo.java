package com.dream.application.common.util.batch.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FootballApiInfo {
    private final String keyNameKey;
    private final String keyNameValue;
    private final String hostNameKey;
    private final String hostNameValue;

    public FootballApiInfo(@Value("${football-api.key-name.key}") String keyNameKey,
                           @Value("${football-api.key-name.value}") String keyNameValue,
                           @Value("${football-api.host-name.key}") String hostNameKey,
                           @Value("${football-api.host-name.value}") String hostNameValue) {
        this.keyNameKey = keyNameKey;
        this.keyNameValue = keyNameValue;
        this.hostNameKey = hostNameKey;
        this.hostNameValue = hostNameValue;
    }
}
