package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class TweetData {

    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("author_id")
    private String authorId;
    @JsonProperty("created_at")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

class Includes {

    @JsonProperty("users")
    private List<User> users = null;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}

public class TwitterObject {

    @JsonProperty("data")
    private TweetData data;
    @JsonProperty("includes")
    private Includes includes;

    public TweetData getData() {
        return data;
    }

    public void setData(TweetData data) {
        this.data = data;
    }

    public Includes getIncludes() {
        return includes;
    }

    public void setIncludes(Includes includes) {
        this.includes = includes;
    }

}

class User {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}