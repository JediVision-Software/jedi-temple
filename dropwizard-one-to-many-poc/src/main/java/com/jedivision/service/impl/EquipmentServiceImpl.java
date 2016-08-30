package com.jedivision.service.impl;

import com.jedivision.dao.EquipmentDao;
import com.jedivision.entity.Equipment;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.EquipmentService;

import java.util.List;

import static com.jedivision.constants.ApplicationConstants.EQUIPMENT_NOT_FOUND;

public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentDao equipmentDao;

    public EquipmentServiceImpl(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    @Override
    public List<Equipment> findAllEquipments() {
        return equipmentDao.findAll();
    }

    @Override
    public Equipment findEquipmentById(int equipmentId) {
        Equipment equipment = equipmentDao.findById(equipmentId);
        if (equipment != null) {
            return equipment;
        } else {
            throw new ApplicationException(EQUIPMENT_NOT_FOUND);
        }
    }

    @Override
    public Equipment createEquipment(Equipment equipment) {
        equipment.setId(null);
        return equipmentDao.create(equipment);
    }

    @Override
    public Equipment updateEquipment(int equipmentId, Equipment equipment) {
        equipment.setId(equipmentId);
        return equipmentDao.create(equipment);
    }

    @Override
    public void deleteEquipment(int equipmentId) {
        Equipment equipment = equipmentDao.findById(equipmentId);
        if (equipment != null) {
            equipmentDao.delete(equipment);
        } else {
            throw new ApplicationException(EQUIPMENT_NOT_FOUND);
        }
    }
}
