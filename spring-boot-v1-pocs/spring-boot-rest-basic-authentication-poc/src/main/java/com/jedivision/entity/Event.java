package com.jedivision.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@EqualsAndHashCode
@ToString
public class Event {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private String place;

    @Getter
    private int price;
}
