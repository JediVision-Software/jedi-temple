package com.jedivision.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EquipmentRatedForJedi {
    private int totalCount;
    private List<RatedEquipment> equipments;
}
