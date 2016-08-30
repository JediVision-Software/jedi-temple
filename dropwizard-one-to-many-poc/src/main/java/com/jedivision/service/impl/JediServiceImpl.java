package com.jedivision.service.impl;

import com.jedivision.dao.EquipmentDao;
import com.jedivision.dao.JediDao;
import com.jedivision.entity.Equipment;
import com.jedivision.entity.EquipmentType;
import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rank;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.JediService;

import java.util.List;

import static com.jedivision.constants.ApplicationConstants.JEDI_NOT_FOUND;
import static com.jedivision.constants.ApplicationConstants.JEDI_OR_EQUIPMENT_NOT_FOUND;
import static com.jedivision.entity.EquipmentType.ARMOR;
import static com.jedivision.entity.EquipmentType.LIGHTSABER;
import static com.jedivision.entity.EquipmentType.ROBE;
import static com.jedivision.entity.Rank.MASTER;
import static com.jedivision.entity.Rank.PADAWAN;

public class JediServiceImpl implements JediService {
    private final JediDao jediDao;
    private final EquipmentDao equipmentDao;

    public JediServiceImpl(JediDao jediDao, EquipmentDao equipmentDao) {
        this.jediDao = jediDao;
        this.equipmentDao = equipmentDao;
    }

    @Override
    public void indexJedi() {
        // Yoda
        createJedi("Yoda", MASTER);
        // Obi-Wan
        createJedi("Obi-Wan", PADAWAN);
        // Equipments
        createEquipment(LIGHTSABER, 25);
        createEquipment(ROBE, 5);
        createEquipment(LIGHTSABER, 20);
        createEquipment(ARMOR, 10);
        // Add equipments to jedis
        addEquipmentToJedi(1, 1);
        addEquipmentToJedi(1, 2);
        addEquipmentToJedi(2, 3);
        addEquipmentToJedi(2, 4);
    }

    @Override
    public List<Jedi> findAllJedi() {
        return jediDao.findAll();
    }

    @Override
    public Jedi findJediById(int jediId) {
        Jedi jedi = jediDao.findById(jediId);
        if (jedi != null) {
            return jedi;
        } else {
            throw new ApplicationException(JEDI_NOT_FOUND);
        }
    }

    @Override
    public Jedi createJedi(Jedi jedi) {
        jedi.setId(null);
        return jediDao.create(jedi);
    }

    @Override
    public Jedi updateJedi(int jediId, Jedi jedi) {
        jedi.setId(jediId);
        return jediDao.create(jedi);
    }

    @Override
    public void deleteJedi(int jediId) {
        Jedi jedi = jediDao.findById(jediId);
        if (jedi != null) {
            jediDao.delete(jedi);
        } else {
            throw new ApplicationException(JEDI_NOT_FOUND);
        }
    }

    @Override
    public Equipment addEquipmentToJedi(int jediId, int equipmentId) {
        Jedi jedi = jediDao.findById(jediId);
        Equipment equipment = equipmentDao.findById(equipmentId);
        if (jedi != null && equipment != null) {
            equipment.setJedi(jedi);
            equipmentDao.create(equipment);
            return equipment;
        } else {
            throw new ApplicationException(JEDI_OR_EQUIPMENT_NOT_FOUND);
        }
    }

    @Override
    public void removeEquipmentsFromJedi(int jediId) {
        Jedi jedi = jediDao.findById(jediId);
        if (jedi != null) {
            List<Equipment> equipments = jedi.getEquipments();
            equipments.forEach(equipmentDao::delete);
        } else {
            throw new ApplicationException(JEDI_NOT_FOUND);
        }
    }

    private void createJedi(String name, Rank rank) {
        Jedi jedi = new Jedi();
        jedi.setName(name);
        jedi.setRank(rank);
        jediDao.create(jedi);
    }

    private void createEquipment(EquipmentType type, Integer value) {
        Equipment equipment = new Equipment();
        equipment.setType(type);
        equipment.setValue(value);
        equipmentDao.create(equipment);
    }
}
