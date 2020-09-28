package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RuleAddResponse {

    private List<Datum> data = null;
    private Meta meta;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}

class SummaryA {

    private Integer created;
    @JsonProperty("not_created")
    private Integer notCreated;
    private Integer valid;
    private Integer invalid;

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    @JsonProperty("not_created")
    public Integer getNotCreated() {
        return notCreated;
    }

    @JsonProperty("not_created")
    public void setNotCreated(Integer notCreated) {
        this.notCreated = notCreated;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getInvalid() {
        return invalid;
    }

    public void setInvalid(Integer invalid) {
        this.invalid = invalid;
    }

}