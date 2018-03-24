package com.forcelate.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private Long userId;
}
