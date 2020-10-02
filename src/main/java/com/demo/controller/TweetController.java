package com.demo.controller;

import com.demo.dto.RuleAddRequest;
import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import com.demo.service.ITweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TweetController {

    @Autowired
    private ITweetService tweetService;

    /**
     * Endpoint for resetting rules
     *
     * @param ruleDto: Add request object
     * @see RuleAddRequest
     * @return Object: Twitter object for adding rules
     */
    @PostMapping("rules")
    public Object addRules(@RequestBody RuleAddRequest ruleDto) {
        return tweetService.resetRule(ruleDto);
    }

    /**
     * Endpoint for removing all rules
     *
     * @return ResponseEntity<Object>: Twitter object after removing existing rules
     */
    @DeleteMapping("rules")
    public ResponseEntity<Object> removeRules() {
        tweetService.removeRules();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for fetching older tweets
     *
     * @param page: Pagination page size
     * @return Page<TweetView>
     */
    @GetMapping("history")
    public Page<TweetView> tweets(@RequestParam Short page) {
        return tweetService.getOldTweets(page);
    }

    /**
     * Endpoint for fetching live tweets
     *
     * @return List<TweetEntity>
     */
    @GetMapping("live")
    public List<TweetEntity> liveTweet() {
        return tweetService.getLatestTweet();
    }

}
