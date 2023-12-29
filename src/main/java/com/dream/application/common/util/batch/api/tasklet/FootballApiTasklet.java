package com.dream.application.common.util.batch.api.tasklet;

import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.dto.FootballApiBaseResponse;
import com.dream.application.common.util.batch.dto.FootballApiInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@StepScope
public class FootballApiTasklet<T> implements Tasklet {

    private static final String BASE_URI = "https://api-football-v1.p.rapidapi.com/";
    private final FootballApiInfo footballApiInfo;

    private final ItemBuffer<T> responseBuffer;

    private final String endPoint;
    private final String method;
    private final HttpRequest.BodyPublisher requestBody;

    @Builder
    public FootballApiTasklet(@NonNull ItemBuffer<T> responseBuffer,
                              @NotBlank String endPoint,
                              @NotBlank String method,
                              HttpRequest.BodyPublisher requestBody,
                              FootballApiInfo footballApiInfo) {
        this.responseBuffer = responseBuffer;
        this.endPoint = endPoint;
        this.method = method;
        this.requestBody = Objects.requireNonNullElseGet(requestBody,
                HttpRequest.BodyPublishers::noBody);
        this.footballApiInfo = footballApiInfo;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        //HttpRequest.BodyPublishers.noBody()
        //"v3/fixtures/events?fixture=215662"
        HttpRequest request = getRequest();

        HttpResponse<String> response = sendGetRequest(request);

        // error 검증
        if (isInvalidResponse(response)) {
            return RepeatStatus.FINISHED;
        }

        // 정상인 경우 확인
        FootballApiBaseResponse<T> responseBody = getResponseBody(response);

        if (responseBody.isError()) {
            return RepeatStatus.FINISHED;
        }

        responseBuffer.addAll(responseBody.getResponse());

        return RepeatStatus.FINISHED;
    }

    private FootballApiBaseResponse<T> getResponseBody(HttpResponse<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), FootballApiBaseResponse.class);
    }

    private static boolean isInvalidResponse(HttpResponse<String> response) {
        return response.statusCode() != 200;
    }

    private static HttpResponse<String> sendGetRequest(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpRequest getRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URI + endPoint))
                .header(footballApiInfo.getKeyNameKey(), footballApiInfo.getKeyNameValue())
                .header(footballApiInfo.getHostNameKey(), footballApiInfo.getHostNameValue())
                .method(method, requestBody)
                .build();
    }
}
