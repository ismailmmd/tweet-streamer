package com.demo.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RuleDeleteRequestTest {

    @Test
    void objectCreation() {
        RuleDeleteRequest ruleDeleteRequest = new RuleDeleteRequest(new ArrayList<>());
        assertEquals(0, ruleDeleteRequest.getIds().size());
        RuleDeleteRequest ruleDeleteRequest1 = new RuleDeleteRequest();
        ruleDeleteRequest1.setIds(new ArrayList<>());
        assertEquals(0, ruleDeleteRequest1.getIds().size());
    }
}