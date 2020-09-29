package com.demo.controller;

import com.demo.dto.RuleAddRequest;
import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import com.demo.service.ITweetService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;

@RestController
public class TweetController {

    private static final Logger log = LoggerFactory.getLogger(TweetController.class);

    @Autowired
    private ITweetService tweetService;

//    @GetMapping("rules")
//    public Object rules() {
//        return tweetService.getRules();
//    }

    @PostMapping("rules")
    public Object addRules(@RequestBody RuleAddRequest ruleDto) {
        return tweetService.resetRule(ruleDto);
    }

    @DeleteMapping("rules")
    public ResponseEntity<Object> removeRules(){
        tweetService.removeRules();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "stream", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamingResponseBody> stream(final HttpServletResponse response) {
        response.setContentType("application/json");
        StreamingResponseBody stream = outputStream -> {
            try {
                tweetService.stream(outputStream);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(outputStream);
            }
        };
        log.info("steaming response");
        return new ResponseEntity<>(stream, HttpStatus.OK);
    }

    @GetMapping("history")
    public Page<TweetView> tweets(@RequestParam Short page) {
        return tweetService.getOldTweets(page);
    }

}
