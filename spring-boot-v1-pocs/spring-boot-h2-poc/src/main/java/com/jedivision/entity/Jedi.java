package com.jedivision.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "jedi")

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Jedi {

    @Setter
    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    @Getter
    @Setter
    @Column(name = "full_name")
    private String fullName;

    @Setter
    @Column(name = "rank")
    private Rank rank;

    @Enumerated(EnumType.STRING)
    public Rank getRank() {
        return rank;
    }

    @Getter
    @Setter
    @Column(name = "age")
    private Integer age;

    @Getter
    @Setter
    @Column(name = "force")
    private int force;

    @Setter
    private List<Rating> ratings = new ArrayList<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jedi")
    public List<Rating> getRatings() {
        return this.ratings;
    }
}
