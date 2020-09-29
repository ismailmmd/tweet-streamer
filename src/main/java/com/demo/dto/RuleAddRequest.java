package com.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RuleAddRequest {
    private List<Add> add;
}

@Getter
@Setter
class Add {
    private String value;
    private String tag;
}
