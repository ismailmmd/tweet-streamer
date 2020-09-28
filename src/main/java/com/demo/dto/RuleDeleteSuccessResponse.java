package com.demo.dto;

class SummaryD {

    private Integer deleted;
    private Integer notDeleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getNotDeleted() {
        return notDeleted;
    }

    public void setNotDeleted(Integer notDeleted) {
        this.notDeleted = notDeleted;
    }

}

public class RuleDeleteSuccessResponse {

    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
