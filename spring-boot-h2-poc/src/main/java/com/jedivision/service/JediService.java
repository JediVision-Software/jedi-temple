package com.jedivision.service;

import com.jedivision.dto.EquipmentRatedForJedi;
import com.jedivision.entity.EquipmentType;
import com.jedivision.exception.ApplicationException;

public interface JediService {
    EquipmentRatedForJedi rateEquipmentForJedi(Integer jediId) throws ApplicationException;
    EquipmentRatedForJedi rateEquipmentByTypeForJedi(Integer jediId, EquipmentType type) throws ApplicationException;
}
