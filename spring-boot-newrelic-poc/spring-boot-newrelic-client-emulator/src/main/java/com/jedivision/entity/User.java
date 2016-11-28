package com.jedivision.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
}
