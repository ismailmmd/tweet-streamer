package com.demo.service;

import com.demo.dto.RuleAddRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

public interface ITweetService {
    Object resetRule(RuleAddRequest ruleDtos) throws JsonProcessingException;
    Object getRules();
    Object stream(OutputStream outputStream) throws URISyntaxException, IOException;
}
