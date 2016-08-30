package com.jedivision.dao;

import com.jedivision.entity.Equipment;
import com.jedivision.entity.Jedi;
import com.jedivision.test.InitializationUtils;
import com.jedivision.test.runner.ApplicationDaoRunner;
import org.junit.Test;

import java.util.List;

import static com.jedivision.constants.ApplicationTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EquipmentDaoTest extends ApplicationDaoRunner {

    private EquipmentDao daoUnderTest = new EquipmentDao(getSession().getSessionFactory());

    @Test
    public void findAll() {
        // Act
        List<Equipment> result = daoUnderTest.findAll();

        // Assert
        assertThat(result).hasSize(EQUIPMENTS_NUMBER);
    }

    @Test
    public void findByIdEquipmentIsMissing() {
        // Act
        Equipment result = daoUnderTest.findById(MISSING_EQUIPMENT);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    public void findById() {
        // Act
        Equipment result = daoUnderTest.findById(PRESENT_EQUIPMENT);

        // Assert
        assertThat(result.getType()).isEqualTo(EQUIPMENT_TYPE);
        assertThat(result.getValue()).isEqualTo(EQUIPMENT_VALUE);
        assertThat(result.getJedi().getId()).isEqualTo(EQUIPMENT_JEDI_ID);
    }

    @Test
    public void create() {
        // Arrange
        Equipment equipment = InitializationUtils.entity(Equipment.class);
        Jedi jedi = InitializationUtils.entity(Jedi.class);
        equipment.setJedi(jedi);

        // Act
        Equipment result = daoUnderTest.create(equipment);

        // Assert
        assertThat(equipment).isNotNull();
        assertThat(jedi).isNotNull();
        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo(equipment.getType());
        assertThat(result.getValue()).isEqualTo(equipment.getValue());
        assertThat(result.getJedi()).isEqualTo(equipment.getJedi());
    }

    @Test
    public void delete() {
        // Arrange
        Equipment equipment = daoUnderTest.findById(PRESENT_EQUIPMENT);

        // Act
        daoUnderTest.delete(equipment);
        Equipment result = daoUnderTest.findById(PRESENT_EQUIPMENT);

        // Assert
        assertThat(result).isNull();
    }
}
