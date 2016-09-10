package com.jedivision.service;

import com.jedivision.dto.EquipmentAvgRated;
import com.jedivision.entity.EquipmentType;
import com.jedivision.exception.ApplicationException;

public interface EquipmentService {
    EquipmentAvgRated rateEquipmentByType (EquipmentType type);
    EquipmentAvgRated rateEquipmentByValue (Integer value);
    Double findAvgRating(int equipmentId) throws ApplicationException;
}
