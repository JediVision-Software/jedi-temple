package com.jedivision.resource;

import com.jedivision.dto.EquipmentAvgRated;
import com.jedivision.entity.EquipmentType;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentResource {
    // DAO
    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentResource(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public EquipmentAvgRated rateEquipmentByType(@PathVariable("type") EquipmentType type) {
        return equipmentService.rateEquipmentByType(type);
    }

    @RequestMapping(value = "/minValue/{value}", method = RequestMethod.GET)
    public EquipmentAvgRated rateEquipmentByValue(@PathVariable("value") Integer value) {
        return equipmentService.rateEquipmentByValue(value);
    }

    @RequestMapping(value = "/{equipmentId}", method = RequestMethod.GET)
    public List<String> avgEquipmentRating(@PathVariable("equipmentId") Integer equipmentId) throws ApplicationException {
        return Collections.singletonList("avgRating: " + equipmentService.findAvgRating(equipmentId));
    }
}
