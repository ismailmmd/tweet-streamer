package com.demo.controller;

import com.demo.dto.RuleAddRequest;
import com.demo.service.ITweetService;
import com.demo.service.TweetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class TweetController {

    private static final Logger log = LoggerFactory.getLogger(TweetController.class);

    @Autowired
    private ITweetService tweetService;

    @GetMapping()
    public String hello() {
        return "hello world!!";
    }

    @GetMapping("rules")
    public Object rules() {
        return tweetService.getRules();
    }

    @PostMapping("rules")
    public Object addRules(@RequestBody RuleAddRequest ruleDto) throws JsonProcessingException {
        return tweetService.resetRule(ruleDto);
    }

    @GetMapping(value = "stream", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamingResponseBody> stream(final HttpServletResponse response) {
        response.setContentType("application/zip");
        StreamingResponseBody stream = outputStream -> {
            try {
                tweetService.stream(response.getOutputStream());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        };
        log.info("steaming response {} ", stream);
        return new ResponseEntity(stream, HttpStatus.OK);
    }

}
