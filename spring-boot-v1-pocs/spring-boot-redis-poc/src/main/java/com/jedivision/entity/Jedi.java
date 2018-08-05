package com.jedivision.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
public class Jedi implements Serializable {
    private String fullName;
    private Integer age;
    private int force;
}
