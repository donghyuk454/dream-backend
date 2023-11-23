package com.dream.application.common.util.batch.tasklet;

import com.dream.application.common.util.batch.api.ItemBuffer;
import com.dream.application.common.util.batch.dto.FootballApiInfo;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@StepScope
public class FootballApiTasklet<T> implements Tasklet {

    private static final String BASE_URI = "https://api-football-v1.p.rapidapi.com/";
    private final FootballApiInfo footballApiInfo;

    @Autowired
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
        this.requestBody = Objects.requireNonNullElseGet(requestBody, HttpRequest.BodyPublishers::noBody);
        this.footballApiInfo = footballApiInfo;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        //HttpRequest.BodyPublishers.noBody()
        //"v3/fixtures/events?fixture=215662"
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URI + endPoint))
                .header(footballApiInfo.getKeyNameKey(), footballApiInfo.getKeyNameValue())
                .header(footballApiInfo.getHostNameKey(), footballApiInfo.getHostNameValue())
                .method(method, requestBody)
                .build();

        HttpResponse<String> response = HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // error 검증

        // 정상인 경우 확인
        response.body();

        return null;
    }
}
