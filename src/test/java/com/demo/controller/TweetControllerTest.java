package com.demo.controller;

import com.demo.dto.RuleAddRequest;
import com.demo.service.ITweetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TweetController.class)
class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ITweetService tweetService;

    @Test
    void addRules_thenReturn200() throws Exception {
        String requestBody = "{\"add\":[{\"value\":\"ndtv\",\"tag\":\"\"}]}";
        mockMvc.perform(post("/rules")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void addRules_callService() throws Exception {
        String requestBody = "{\"add\":[{\"value\":\"ndtv\",\"tag\":\"\"}]}";
        mockMvc.perform(post("/rules")
                .contentType("application/json")
                .content(requestBody));
        ArgumentCaptor<RuleAddRequest> captor = ArgumentCaptor.forClass(RuleAddRequest.class);
        verify(tweetService, times(1)).resetRule(captor.capture());
        assertThat(captor.getValue().getAdd().isEmpty()).isFalse();
    }

    @Test
    void removeRules() throws Exception {
        mockMvc.perform(delete("/rules"))
                .andExpect(status().isOk());
    }

    @Test
    void removeRules_callService() throws Exception {
        mockMvc.perform(delete("/rules"));
        verify(tweetService, times(1)).removeRules();
    }

    @Test
    void tweets_withoutPage() throws Exception {
        mockMvc.perform(get("/history"))
                .andExpect(status().is(400));
    }

    @Test
    void tweets_withPage() throws Exception {
        mockMvc.perform(get("/history?page=0"))
                .andExpect(status().isOk());
    }

    @Test
    void liveTweet() throws Exception {
        mockMvc.perform(get("/live"))
                .andExpect(status().isOk());
    }
}