package com.jedivision.service.impl;

import com.jedivision.dao.EquipmentDao;
import com.jedivision.dto.AvgRatedEquipment;
import com.jedivision.dto.EquipmentAvgRated;
import com.jedivision.entity.Equipment;
import com.jedivision.entity.EquipmentType;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.EquipmentService;
import com.jedivision.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.jedivision.constants.ApplicationConstants.EQUIPMENT_NOT_FOUND;
import static com.jedivision.utils.AverageUtils.getEquipmentAvgRating;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    // DAO
    private final EquipmentDao equipmentDao;

    @Autowired
    public EquipmentServiceImpl(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    @Override
    public EquipmentAvgRated rateEquipmentByType(EquipmentType type) {
        List<AvgRatedEquipment> equipments = StreamUtils.stream(equipmentDao.findAll())
                .filter(equipment -> equipment.getType().equals(type))
                .map(equipment -> AvgRatedEquipment.builder()
                        .name(equipment.getName())
                        .avgRate(getEquipmentAvgRating(equipment))
                        .build())
                .collect(Collectors.toList());
        equipments.sort((e1, e2) -> e2.getAvgRate().compareTo(e1.getAvgRate()));
        return EquipmentAvgRated.builder()
                .equipments(equipments)
                .build();
    }

    @Override
    public EquipmentAvgRated rateEquipmentByValue(Integer value) {
        List<AvgRatedEquipment> equipments = StreamUtils.stream(equipmentDao.findAll())
                .filter(equipment -> equipment.getValue() >= value)
                .map(equipment -> AvgRatedEquipment.builder()
                        .name(equipment.getName())
                        .avgRate(getEquipmentAvgRating(equipment))
                        .build())
                .collect(Collectors.toList());
        equipments.sort((e1, e2) -> e2.getAvgRate().compareTo(e1.getAvgRate()));
        return EquipmentAvgRated.builder()
                .equipments(equipments)
                .build();
    }

    @Override
    public Double findAvgRating(int equipmentId) throws ApplicationException {
        Equipment equipment = equipmentDao.findOne(equipmentId);
        if (equipment != null) {
            return getEquipmentAvgRating(equipment);
        } else {
            throw new ApplicationException(EQUIPMENT_NOT_FOUND);
        }
    }
}
