package com.demo;

import com.demo.service.ITweetService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class TweetStreamer {
	public static void main(String[] args) throws IOException, URISyntaxException {
		ApplicationContext context = SpringApplication.run(TweetStreamer.class, args);
//		Initialising streaming
		context.getBean(ITweetService.class).stream();
	}
}
