package com.jedivision.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

// JPA
@Entity(name = "equipment")
@NamedQuery(name = "Equipment.findAll", query = "SELECT t FROM equipment t")
// Lombok
@NoArgsConstructor
@EqualsAndHashCode
public class Equipment {

    @Setter
    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

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
    private Integer value;

    @Setter
    private Jedi jedi;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jedi_id")
    public Jedi getJedi() {
        return jedi;
    }
}