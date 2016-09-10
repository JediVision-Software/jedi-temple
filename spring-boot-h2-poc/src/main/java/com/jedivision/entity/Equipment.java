package com.jedivision.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "equipment")

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Equipment {

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
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "type")
    private EquipmentType type;

    @Enumerated(EnumType.STRING)
    public EquipmentType getType() {
        return type;
    }

    @Getter
    @Setter
    @Column(name = "value")
    private int value;

    @Setter
    private List<Rating> ratings = new ArrayList<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
    public List<Rating> getRatings() {
        return this.ratings;
    }
}
