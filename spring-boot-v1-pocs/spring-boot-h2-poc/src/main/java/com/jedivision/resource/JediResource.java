package com.jedivision.resource;

import com.jedivision.dto.EquipmentRatedForJedi;
import com.jedivision.entity.EquipmentType;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jedi")
public class JediResource {
    // Service
    private final JediService jediService;

    @Autowired
    public JediResource(JediService jediService) {
        this.jediService = jediService;
    }

    @RequestMapping(value = "/{jediId}",method = RequestMethod.GET)
    public EquipmentRatedForJedi equipmentRatedForJedi(@PathVariable("jediId") Integer jediId) throws ApplicationException {
        return jediService.rateEquipmentForJedi(jediId);
    }

    @RequestMapping(value = "/{jediId}/type/{type}", method = RequestMethod.GET)
    public EquipmentRatedForJedi equipmentRatedByTypeForJedi(@PathVariable("jediId") Integer jediId,
                                                             @PathVariable("type") EquipmentType type) throws ApplicationException {
        return jediService.rateEquipmentByTypeForJedi(jediId, type);
    }
}
