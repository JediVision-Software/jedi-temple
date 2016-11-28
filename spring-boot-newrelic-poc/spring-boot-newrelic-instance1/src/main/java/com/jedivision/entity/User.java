package com.jedivision.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
}
