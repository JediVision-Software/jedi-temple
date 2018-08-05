package com.jdv.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String text;
}
