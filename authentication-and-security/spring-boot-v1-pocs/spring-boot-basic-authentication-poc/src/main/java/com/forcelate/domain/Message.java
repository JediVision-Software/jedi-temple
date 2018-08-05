package com.forcelate.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String principal;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String text;
}
