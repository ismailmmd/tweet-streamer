package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName(value = "delete")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class RuleDeleteRequest {
    List<String> ids;

    public RuleDeleteRequest() {
    }

    public RuleDeleteRequest(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
