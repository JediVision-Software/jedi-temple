package com.jedivision.dao;

import com.jedivision.entity.Equipment;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class EquipmentDao extends AbstractDAO<Equipment> {

    public EquipmentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Equipment> findAll() {
        return list(namedQuery("Equipment.findAll"));
    }

    public Equipment findById(Integer id) {
        Optional<Equipment> optionalEquipment = Optional.ofNullable(get(id));
        if (optionalEquipment.isPresent()) {
            return optionalEquipment.get();
        } else {
            return null;
        }
    }

    public Equipment create(Equipment equipment) {
        return persist(equipment);
    }

    public void delete(Equipment equipment) {
        currentSession().delete(equipment);
    }
}
