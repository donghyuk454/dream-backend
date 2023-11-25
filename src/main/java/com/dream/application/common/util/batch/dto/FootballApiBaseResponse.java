package com.dream.application.common.util.batch.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class FootballApiBaseResponse<T> {

    private String get;
    private List<String> parameters;
    private List<String> errors;
    private Integer results;
    private List<T> response;

    public List<T> getResponse() {
        return response;
    }

    public boolean isError() {
        return !errors.isEmpty();
    }

    public Integer getResponseCount() {
        return results;
    }
}
