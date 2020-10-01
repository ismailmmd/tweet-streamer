package com.demo.service;

import com.demo.dto.RuleAddRequest;
import com.demo.entity.repo.TweetRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
class TweetServiceTest {

    @Mock
    TweetRepo tweetRepo;
    ITweetService tweetService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;
    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        tweetService = new TweetService();
    }


    @Test
    void resetRule() throws URISyntaxException {
        tweetService.resetRule(new RuleAddRequest());
        verify(tweetService, times(1)).removeRules();
        verify(tweetRepo, times(1)).deleteAll();
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("post_rules_uri")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                );

        mockServer.verify();
    }

    @Test
    void removeRules() {
    }

    @Test
    void stream() {
    }

    @Test
    void getOldTweets() {
    }

    @Test
    void getLatestTweet() {
    }
}