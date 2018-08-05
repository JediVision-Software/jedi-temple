package com.jedivision.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "jedi")
@NoArgsConstructor
@AllArgsConstructor
public class Jedi {
    @Id
    private String id;
    private String fullName;
    private int age;
}
