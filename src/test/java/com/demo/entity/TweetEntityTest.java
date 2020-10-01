package com.demo.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TweetEntityTest {

    @Test
    void entityCreation() {
        TweetEntity tweetEntity = new TweetEntity("tweet", LocalDateTime.now());
        assertEquals("tweet", tweetEntity.getData());
        assertNotNull(tweetEntity.getDateTime());
        assertNull(tweetEntity.getId());
    }
}