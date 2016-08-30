package com.jedivision.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// JPA
@Entity(name = "jedi")
@NamedQuery(name = "Jedi.findAll", query = "SELECT t FROM jedi t")
// Lombok
@NoArgsConstructor
@EqualsAndHashCode
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
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "rank")
    private Rank rank;

    @Enumerated(EnumType.STRING)
    public Rank getRank() {
        return rank;
    }

    @Setter
    private List<Equipment> equipments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "jedi")
    public List<Equipment> getEquipments() {
        return this.equipments;
    }
}
