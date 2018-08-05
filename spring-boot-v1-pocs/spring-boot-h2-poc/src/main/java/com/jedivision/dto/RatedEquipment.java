package com.jedivision.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RatedEquipment {
    private String name;
    private Integer rate;
}
