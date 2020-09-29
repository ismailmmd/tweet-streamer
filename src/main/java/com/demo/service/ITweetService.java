package com.demo.service;

import com.demo.dto.RuleAddRequest;
import com.demo.entity.TweetView;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ITweetService {
    Object resetRule(RuleAddRequest ruleDtos);
    void removeRules();
    void stream() throws URISyntaxException, IOException;
    Page<TweetView> getOldTweets(Short page);
    TweetView getLatestTweet();
}
