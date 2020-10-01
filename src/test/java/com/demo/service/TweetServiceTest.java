package com.demo.service;

import com.demo.dto.RuleAddRequest;
import com.demo.dto.RuleObject;
import com.demo.entity.repo.TweetRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetServiceTest {

    @Mock
    TweetRepo tweetRepo;
    @InjectMocks
    TweetService tweetService;
    @Mock
    private RestTemplate restTemplate;

    @Test
    void resetRule_noExistingRules() {
        when(restTemplate.getForEntity(anyString(),any())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(restTemplate.postForEntity(anyString(),any(), any(Class.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        tweetService.resetRule(new RuleAddRequest());
        verify(tweetRepo, times(1)).deleteAll();
        verify(restTemplate, times(1)).postForEntity(anyString(), any(), any());
    }

    @Test
    void resetRule() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RuleObject ruleObject = mapper.readValue("{\"data\":[{\"value\":\"dhruv_rathee\",\"tag\":\"\",\"id\":" +
                "\"1311659354437812225\"}]}", RuleObject.class);
        when(restTemplate.getForEntity(anyString(),any())).thenReturn(new ResponseEntity<>(ruleObject, HttpStatus.OK));
        when(restTemplate.postForEntity(anyString(),any(), any(Class.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        tweetService.resetRule(new RuleAddRequest());
        verify(restTemplate, times(2)).postForEntity(anyString(), any(), any());
    }

    @Test
    void stream() throws IOException, URISyntaxException {
//        tweetService.stream();
    }

    @Test
    void getOldTweets() {
        tweetService.getOldTweets((short) 0);
        verify(tweetRepo, times(1)).findAllProjectedByOrderByDateTimeDesc(any());
    }

    @Test
    void getLatestTweet() {
        tweetService.getLatestTweet();
        verify(tweetRepo, times(1)).findTop20ByOrderByDateTimeDesc();
    }
}