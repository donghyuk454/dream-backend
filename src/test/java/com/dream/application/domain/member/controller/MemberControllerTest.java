package com.dream.application.domain.member.controller;

import com.dream.application.common.session.SessionConst;
import com.dream.application.domain.member.dto.SessionMember;
import com.dream.application.domain.member.service.MemberService;
import com.dream.application.domain.member.service.dto.response.FindMemberServiceResponse;
import com.dream.application.domain.member.service.dto.response.PlayerInfoDto;
import com.dream.application.web.auth.interceptor.LoginInterceptor;
import com.dream.application.web.exception.ExceptionHandlers;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MemberControllerTest {

    private MockMvc mvc;

    @Autowired
    private MemberController memberController;

    @MockBean
    private MemberService memberService;

    private static final String BASE_URL = "/api/v1/members";
    private static final String LOGIN_URL = "/api/v1/auth/login";

    PlayerInfoDto player1 = new PlayerInfoDto(1L, "손흥민");;
    PlayerInfoDto player2 = new PlayerInfoDto(2L, "김민재");


    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(memberController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .setControllerAdvice(new ExceptionHandlers())
                .addInterceptors(new LoginInterceptor())
                .build();
    }

    @Test
    @DisplayName("member 를 조회하면 구독 정보까지 모두 조회됩니다.")
    void getMemberDetailInfo() throws Exception {
        //given
        Long memberId = 1L;
        String memberName = "name";

        List<PlayerInfoDto> players = List.of(player1,player2);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_SESSION_KEY, new SessionMember(memberId, memberName));

        when(memberService.findMemberWithSubscription(any()))
                .thenReturn(new FindMemberServiceResponse(1L, memberName, players));

        //when
        //then
        mvc.perform(get(BASE_URL + "/" + memberId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andExpect(jsonPath("$.data.memberName").value(memberName))
                .andExpect(jsonPath("$.data.subscription", hasSize(2)))
                .andExpect(jsonPath("$.data.subscription[0].playerId").value(1))
                .andExpect(jsonPath("$.data.subscription[0].playerName").value("손흥민"))
                .andExpect(jsonPath("$.data.subscription[1].playerId").value(2))
                .andExpect(jsonPath("$.data.subscription[1].playerName").value("김민재"));
    }

    @Test
    @DisplayName("세션 없이 member 를 조회하면 302 redirect 가 발생합니다. (" + LOGIN_URL + ")")
    void getMemberDetailWithNoSession() throws Exception {
        //given
        Long memberId = 1L;
        String memberName = "name";
        List<PlayerInfoDto> players = List.of(player1, player2);

        when(memberService.findMemberWithSubscription(any()))
                .thenReturn(new FindMemberServiceResponse(1L, memberName, players));

        //when
        //then
        mvc.perform(get(BASE_URL + "/" + memberId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(LOGIN_URL));
    }

    @Test
    @DisplayName("구독하는 선수가 없다면 비어있는 data 를 return 합니다. ")
    void getMemberDetailInfoWithNoMember() throws Exception {
        //given
        Long memberId = 1L;
        String memberName = "name";
        List<PlayerInfoDto> players = new ArrayList<>();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_SESSION_KEY, new SessionMember(memberId, memberName));

        when(memberService.findMemberWithSubscription(any()))
                .thenReturn(new FindMemberServiceResponse(1L, memberName, players));

        //when
        //then
        mvc.perform(get(BASE_URL + "/" + memberId)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andExpect(jsonPath("$.data.memberName").value(memberName))
                .andExpect(jsonPath("$.data.subscription", hasSize(0)));
    }
}