package com.demo.service;

import com.demo.dto.RuleAddRequest;
import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Service to implement Streaming APIs
 */
public interface ITweetService {
    /**
     * Method to reset rules
     *
     * @param ruleDtos
     */
    Object resetRule(RuleAddRequest ruleDtos);

    /**
     * Method to clear all rules
     */
    void removeRules();

    /**
     * Method to stream tweets and save to Database
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    void stream() throws URISyntaxException, IOException;

    /**
     * Method to retrive older tweets from Database
     *
     * @param page
     * @return Paginated Tweet object
     */
    Page<TweetView> getOldTweets(Short page);

    /**
     * Get latest 20 tweets
     *
     * @return List of Tweet objects
     */
    List<TweetEntity> getLatestTweet();
}
