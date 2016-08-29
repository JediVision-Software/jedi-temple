package com.jedivision.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

// elastic
@Document(indexName = "jedi", type = "jedi", shards = 1, replicas = 0)
// lombok
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Jedi {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String fullName;

    @Getter
    @Setter
    private Rank rank;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private long force;

    @Getter
    @Setter
    @Singular
    private List<Equipment> equipments;
}
