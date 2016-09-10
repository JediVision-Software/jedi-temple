package com.jedivision.service.impl;

import com.jedivision.dao.JediDao;
import com.jedivision.dto.EquipmentRatedForJedi;
import com.jedivision.dto.RatedEquipment;
import com.jedivision.entity.EquipmentType;
import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rating;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.jedivision.constants.ApplicationConstants.JEDI_NOT_FOUND;

@Service
public class JediServiceImpl implements JediService {
    // DAO
    private final JediDao jediDao;

    @Autowired
    public JediServiceImpl(JediDao jediDao) {
            this.jediDao = jediDao;
        }

    @Override
    public EquipmentRatedForJedi rateEquipmentForJedi(Integer jediId) throws ApplicationException {
        Jedi jedi = jediDao.findOne(jediId);
        if (jedi != null) {
            List<RatedEquipment> equipments = jedi.getRatings().stream()
                    .map(rating -> RatedEquipment.builder()
                            .name(rating.getEquipment().getName())
                            .rate(rating.getRate())
                            .build())
                    .collect(Collectors.toList());
            equipments.sort((e1, e2) -> e2.getRate().compareTo(e1. getRate()));
            return EquipmentRatedForJedi.builder()
                    .totalCount(jedi.getRatings().size())
                    .equipments(equipments)
                    .build();
        } else {
            throw new ApplicationException(JEDI_NOT_FOUND);
        }
    }

    @Override
    public EquipmentRatedForJedi rateEquipmentByTypeForJedi(Integer jediId, EquipmentType type) throws ApplicationException {
        Jedi jedi = jediDao.findOne(jediId);
        if (jedi != null) {
            List<Rating> ratingList = jedi.getRatings().stream()
                    .filter(rating -> rating.getEquipment().getType().equals(type))
                    .collect(Collectors.toList());
            List<RatedEquipment> equipments = ratingList.stream()
                    .map(rating -> RatedEquipment.builder()
                            .name(rating.getEquipment().getName())
                            .rate(rating.getRate())
                            .build())
                    .collect(Collectors.toList());
            equipments.sort((e1, e2) -> e2.getRate().compareTo(e1. getRate()));
            return EquipmentRatedForJedi.builder()
                    .totalCount(ratingList.size())
                    .equipments(equipments)
                    .build();
        } else {
            throw new ApplicationException(JEDI_NOT_FOUND);
        }
    }
}
