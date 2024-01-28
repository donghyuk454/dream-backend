package com.dream.application.domain.member.controller;

import com.dream.application.domain.member.service.MemberService;
import com.dream.application.domain.member.service.dto.response.FindMemberServiceResponse;
import com.dream.application.web.exception.ExceptionHandlers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(memberController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .setControllerAdvice(new ExceptionHandlers())
                .build();
    }

    @Test
    @DisplayName("")
    void getMemberDetailInfo() throws Exception {
        //given
        String memberId = "1";
        String memberName = "name";
        List<String> players = List.of("김민재","손흥민");

        when(memberService.findMemberWithSubscription(any()))
                .thenReturn(new FindMemberServiceResponse(1L, memberName, players));
        //when
        //then
        mvc.perform(get(BASE_URL + "/" + memberId))
                .andExpect(status().isOk());
    }
}