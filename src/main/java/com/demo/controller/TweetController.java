package com.demo.controller;

import com.demo.dto.RuleAddRequest;
import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import com.demo.service.ITweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TweetController {

    private static final Logger log = LoggerFactory.getLogger(TweetController.class);

    @Autowired
    private ITweetService tweetService;

    @PostMapping("rules")
    public Object addRules(@RequestBody RuleAddRequest ruleDto) {
        return tweetService.resetRule(ruleDto);
    }

    @DeleteMapping("rules")
    public ResponseEntity<Object> removeRules(){
        tweetService.removeRules();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("history")
    public Page<TweetView> tweets(@RequestParam Short page) {
        return tweetService.getOldTweets(page);
    }

    @GetMapping("live")
    public TweetEntity liveTweet() {
        return tweetService.getLatestTweet();
    }

}
