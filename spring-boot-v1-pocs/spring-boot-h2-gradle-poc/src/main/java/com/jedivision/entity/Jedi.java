package com.jedivision.entity;

import lombok.*;

import javax.persistence.*;

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
}
