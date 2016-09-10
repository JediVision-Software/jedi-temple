package com.jedivision.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvgRatedEquipment {
    private String name;
    private Double avgRate;
}
