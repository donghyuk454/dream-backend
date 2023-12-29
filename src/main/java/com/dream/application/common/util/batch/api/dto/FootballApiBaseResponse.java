package com.dream.application.common.util.batch.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballApiBaseResponse<P, T> {

    private String get;
    private Paging paging;
    private P parameters;
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
