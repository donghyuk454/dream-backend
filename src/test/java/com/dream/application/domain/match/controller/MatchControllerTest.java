package com.dream.application.domain.match.controller;

import com.dream.application.common.session.SessionConst;
import com.dream.application.domain.match.controller.request.GetMyPlayerMatchesRequest;
import com.dream.application.domain.match.service.MatchService;
import com.dream.application.domain.match.service.dto.response.MatchDto;
import com.dream.application.domain.match.service.dto.response.MyPlayerMatchesServiceResponse;
import com.dream.application.domain.member.dto.SessionMember;
import com.dream.application.web.auth.interceptor.LoginInterceptor;
import com.dream.application.web.exception.ExceptionHandlers;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MatchControllerTest {

    private MockMvc mvc;

    @Autowired
    private MatchController matchController;

    @MockBean
    private MatchService matchService;

    private static final String BASE_URL = "/api/v1/matches";
    private static final String LOGIN_URL = "/api/v1/auth/login";

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(matchController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .setControllerAdvice(new ExceptionHandlers())
                .addInterceptors(new LoginInterceptor())
                .build();
    }

    @Test
    @DisplayName("player 가 속한 팀의 경기 리스트를 반환합니다.")
    void getMyPlayerMatches() throws Exception {
        //given
        Long memberId = 1L;
        String memberName = "name";
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_SESSION_KEY, new SessionMember(memberId, memberName));

        Long playerId = 1L;
        LocalDateTime scheduleDateTime = LocalDateTime.now();
        List<MatchDto> matches = List.of(new MatchDto(1L, "Premier League", "토트넘", "맨유", scheduleDateTime),
                new MatchDto(2L, "Premier League", "첼시", "토트넘", scheduleDateTime));
        when(matchService.getMyPlayerMatches(any()))
                .thenReturn(new MyPlayerMatchesServiceResponse(playerId, matches));

        //when
        //then
        String schedule = getScheduleFormat(scheduleDateTime);
        Gson gson = new Gson();
        String content = gson.toJson(new GetMyPlayerMatchesRequest(2023));
        mvc.perform(get(BASE_URL + "/players/" + playerId)
                        .session(session)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.playerId").value(playerId))
                .andExpect(jsonPath("$.data.season").value(2023))
                .andExpect(jsonPath("$.data.matches", hasSize(2)))
                .andExpect(jsonPath("$.data.matches[0].matchId").value(1L))
                .andExpect(jsonPath("$.data.matches[0].league").value("Premier League"))
                .andExpect(jsonPath("$.data.matches[0].home").value("토트넘"))
                .andExpect(jsonPath("$.data.matches[0].away").value("맨유"))
                .andExpect(jsonPath("$.data.matches[0].schedule").value(schedule))
                .andExpect(jsonPath("$.data.matches[1].matchId").value(2L))
                .andExpect(jsonPath("$.data.matches[1].league").value("Premier League"))
                .andExpect(jsonPath("$.data.matches[1].home").value("첼시"))
                .andExpect(jsonPath("$.data.matches[1].away").value("토트넘"))
                .andExpect(jsonPath("$.data.matches[1].schedule").value(schedule));
    }

    private String getScheduleFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}