package com.demo.service;

import com.demo.dto.*;
import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import com.demo.entity.repo.TweetRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
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
        final String uri = baseUri + RULES;
        HttpEntity<Object> entity = new HttpEntity<>(ruleDto);
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, entity, Object.class);
        log.info("Result - status ({}) has body: {}",response.getStatusCode(),response.hasBody());
        return response.getBody();
    }

    @Override
    public RuleObject getRules() {
        final String uri = baseUri + RULES;
        ResponseEntity<RuleObject> response = restTemplate.getForEntity(uri, RuleObject.class);
        return response.getBody();
    }

    @Override
    public void removeRules() {
        RuleObject rules = getRules();
        if(rules.getData() == null || rules.getData().isEmpty())
            return;
        List<String> ruleIds = rules
                .getData()
                .stream()
                .map(Datum::getId)
                .collect(Collectors.toList());
        final String uri = baseUri + RULES;
        HttpEntity<Object> entity = new HttpEntity<>(new RuleDeleteRequest(ruleIds));
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, entity, Object.class);
        log.info("Result - status ( {} ) has body: {}",response.getStatusCode(),response.hasBody());
    }

    @Async
    @Override
    public void stream(OutputStream outputStream) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder(baseUri+"?tweet.fields=created_at&expansions=author_id");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", token));
        CloseableHttpResponse response = httpClient.execute(httpGet);
        org.apache.http.HttpEntity entity = response.getEntity();
        int count = 0;

        if (null != entity) {
            try (InputStreamReader inputStreamReader = new InputStreamReader((entity.getContent()));
                 BufferedReader reader = new BufferedReader(inputStreamReader);
                 Writer writer = new OutputStreamWriter(outputStream)
                 ) {
                String line = reader.readLine();
                writer.append(line);
                saveTweet(line);
                while (line != null && count < 3) {
                    log.info("steam out {}", line);
                    line = reader.readLine();
                    writer.append(line);
                    saveTweet(line);
                    count++;
                    writer.flush();
                }
            } finally {
                response.close();
                httpClient.close();
            }
        }
    }

    @Override
    public Page<TweetView> getOldTweets(Short page) {
        return tweetRepo.findAllProjectedBy(PageRequest.of(page, 20));
    }

    @Async
    private void saveTweet(String json){
        tweetRepo.save(new TweetEntity(json));
    }
}
