package com.jedivision.document;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Equipment {
    @Getter
    @Setter
    private EquipmentType equipmentType;

    @Getter
    @Setter
    private long value;
}
