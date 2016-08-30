package com.jedivision.service;

import com.jedivision.entity.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> findAllEquipments();
    Equipment findEquipmentById(int equipmentId);
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(int equipmentId, Equipment equipment);
    void deleteEquipment(int equipmentId);
}
