package com.forcelate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String title;
}
