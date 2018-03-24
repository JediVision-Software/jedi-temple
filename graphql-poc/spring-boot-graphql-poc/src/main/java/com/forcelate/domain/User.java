package com.forcelate.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class User {
    private Long id;
    private String username;
    private int age;
    private int rank;
    private List<Long> bookIds;
}
