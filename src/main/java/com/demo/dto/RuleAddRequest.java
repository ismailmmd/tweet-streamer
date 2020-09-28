package com.demo.dto;

import java.util.List;

public class RuleAddRequest {
    private List<Add> add;

    public List<Add> getAdd() {
        return add;
    }

    public void setAdd(List<Add> add) {
        this.add = add;
    }
}

class Add {
    private String value;
    private String tag;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
