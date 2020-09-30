package com.demo.service;

import com.demo.dto.*;
import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import com.demo.entity.repo.TweetRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService implements ITweetService {

    private static final Logger log = LoggerFactory.getLogger(TweetService.class);

    private static final String RULES = "/rules";

    @Value("${twitter.config.uri}")
    private String baseUri;
    @Value("${twitter.config.token}")
    private String token;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    TweetRepo tweetRepo;

    @Override
    public Object resetRule(RuleAddRequest ruleDto) {
        removeRules();
        tweetRepo.deleteAll();
        final String uri = baseUri + RULES;
        HttpEntity<Object> entity = new HttpEntity<>(ruleDto);
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, entity, Object.class);
        log.info("Reset Rules : Result - status ({}) has body: {}", response.getStatusCode(), response.hasBody());
        return response.getBody();
    }

    @Override
    public void removeRules() {
        tweetRepo.deleteAll();
        RuleObject rules = getRules();
        if (rules.getData() == null || rules.getData().isEmpty())
            return;
        List<String> ruleIds = rules
                .getData()
                .stream()
                .map(Datum::getId)
                .collect(Collectors.toList());
        final String uri = baseUri + RULES;
        HttpEntity<Object> entity = new HttpEntity<>(new RuleDeleteRequest(ruleIds));
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, entity, Object.class);
        log.info("Remove Rules : Result - status ( {} ) has body: {}", response.getStatusCode(), response.hasBody());
    }

    @Async
    @Override
    public void stream() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder(baseUri + "?tweet.fields=created_at&expansions=author_id");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", token));
        HttpResponse response = httpClient.execute(httpGet);
        org.apache.http.HttpEntity entity = response.getEntity();
        if (null != entity) {
            log.info("Started streaming at {}", LocalDateTime.now());
            try (InputStreamReader inputStreamReader = new InputStreamReader((entity.getContent()));
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                saveTweet(line);
                while (line != null) {
                    log.info("steaming out {}", line);
                    line = reader.readLine();
                    saveTweet(line);
                }
            } catch (Exception exception) {
                log.error("Exception occurred during streaming {}", exception.getMessage());
            } finally {
                log.info("Streaming closed");
            }
        }
    }

    @Override
    public Page<TweetView> getOldTweets(Short page) {
        return tweetRepo.findAllProjectedByOrderByDateTimeDesc(PageRequest.of(page, 20));
    }

    @Override
    public TweetEntity getLatestTweet() {
        return tweetRepo.findFirstByOrderByDateTimeDesc();
    }

    private RuleObject getRules() {
        final String uri = baseUri + RULES;
        ResponseEntity<RuleObject> response = restTemplate.getForEntity(uri, RuleObject.class);
        return response.getBody();
    }

    @Async
    private void saveTweet(String json) {
        if (json == null || json.length() == 0) return;
        tweetRepo.save(new TweetEntity(json, LocalDateTime.now()));
    }
}
