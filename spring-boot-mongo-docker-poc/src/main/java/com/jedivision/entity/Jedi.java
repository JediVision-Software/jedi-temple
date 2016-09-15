package com.jedivision.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "jedi")
public class Jedi {
    @Id
    private String id;
    private String fullName;
}
