package com.demo.service;

import com.demo.dto.RuleAddRequest;
import com.demo.entity.TweetView;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

public interface ITweetService {
    Object resetRule(RuleAddRequest ruleDtos);
    Object getRules();
    void removeRules();
    void stream(OutputStream outputStream) throws URISyntaxException, IOException;
    Page<TweetView> getOldTweets(Short page);
}
