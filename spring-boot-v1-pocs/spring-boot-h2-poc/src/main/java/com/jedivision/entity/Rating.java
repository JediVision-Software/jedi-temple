package com.jedivision.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "rating")

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Rating {

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
    @Column(name = "rate")
    private Integer rate;

    @JsonIgnore
    @Setter
    private Equipment equipment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    public Equipment getEquipment() {
        return equipment;
    }

    @JsonIgnore
    @Setter
    private Jedi jedi;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "jedi_id")
    public Jedi getJedi() {
        return jedi;
    }
}
