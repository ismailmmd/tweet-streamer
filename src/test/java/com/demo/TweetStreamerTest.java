package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
class TweetStreamerTest {

	@Test
	void contextLoads() throws IOException, URISyntaxException {
		TweetStreamer.main(new String[] {});
	}

}
