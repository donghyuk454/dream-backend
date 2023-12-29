package com.dream.application.common.util.batch.dto;

import com.dream.application.common.util.batch.api.dto.FootballApiBaseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FootballApiBaseResponseTest {

    @Test
    @DisplayName("errors 가 비어 있으면 성공입니다.")
    void emptyErrorMeansSuccess() {
        //given
        FootballApiBaseResponse response = new FootballApiBaseResponse(null,null,null,List.of(),null,null);

        //when
        boolean result = response.isError();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("errors 가 비어 있지 않으면 실패입니다.")
    void notEmptyErrorMeansError() {
        //given
        FootballApiBaseResponse response = new FootballApiBaseResponse(null,null,null,List.of(""),null,null);

        //when
        boolean result = response.isError();

        //then
        assertThat(result).isTrue();
    }
}