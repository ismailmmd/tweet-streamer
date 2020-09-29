package com.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TweetEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String data;
    private LocalDateTime dateTime;

    public TweetEntity(String tweet, LocalDateTime localDateTime) {
        this.data = tweet;
        this.dateTime = localDateTime;
    }

}
