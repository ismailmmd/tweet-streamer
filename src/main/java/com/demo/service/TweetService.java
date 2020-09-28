package com.demo.service;

import com.demo.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService implements ITweetService {

    private static final Logger log = LoggerFactory.getLogger(TweetService.class);

    private final String RULES = "/rules";

    @Value("${twitter.config.uri}")
    private String baseUri;
    @Value("${twitter.config.token}")
    private String token;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Object resetRule(RuleAddRequest ruleDto) throws JsonProcessingException {
        removeRules();
        final String uri = baseUri + RULES;
        HttpEntity<Object> entity = new HttpEntity<>(ruleDto);
        ResponseEntity<RuleAddResponse> response = restTemplate.postForEntity(uri, entity, RuleAddResponse.class);
        log.info("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());
        List<Datum> rules = response.getBody().getData();
        return response.getBody();
    }

    @Override
    public RuleObject getRules() {
        final String uri = baseUri + RULES;
        ResponseEntity<RuleObject> response = restTemplate.getForEntity(uri, RuleObject.class);
            System.out.println("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());
        return response.getBody();
    }

    private void removeRules() {
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
        ResponseEntity<RuleDeleteErrorResponse> response = restTemplate.postForEntity(uri, entity, RuleDeleteErrorResponse.class);
        log.info("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());
    }

    @Override
    public Object stream(OutputStream outputStream) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder(baseUri+"?tweet.fields=created_at&expansions=author_id");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", token));

        HttpResponse response = httpClient.execute(httpGet);
        org.apache.http.HttpEntity entity = response.getEntity();
        int count = 0;
        if (null != entity) {
            try (InputStreamReader inputStreamReader = new InputStreamReader((entity.getContent()));
                 BufferedReader reader = new BufferedReader(inputStreamReader);
                 Writer writer = new OutputStreamWriter(outputStream)
                 ) {
                String line = reader.readLine();
                writer.append(line);
                while (line != null && count < 3) {
                    System.out.println(line);
                    line = reader.readLine();
                    writer.append(line);
                    count++;
                    writer.flush();
                }
                entity.getContent().close();
            }
        }
        return null;
    }
}
