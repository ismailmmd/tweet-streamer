package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class TweetEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String data;

    public TweetEntity(String tweet) {
        this.data = tweet;
    }

    public TweetEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
