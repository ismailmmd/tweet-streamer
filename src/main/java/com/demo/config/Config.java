package com.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Value("${twitter.config.token}")
    private String token;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer "+token);
            return execution.execute(request, body);
        })).build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
