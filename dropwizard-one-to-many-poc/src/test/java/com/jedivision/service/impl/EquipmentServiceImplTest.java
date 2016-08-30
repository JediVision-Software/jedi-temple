package com.jedivision.service.impl;

import com.jedivision.dao.EquipmentDao;
import com.jedivision.entity.Equipment;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.EquipmentService;
import com.jedivision.test.InitializationUtils;
import com.jedivision.test.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static com.jedivision.constants.ApplicationConstants.EQUIPMENT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class EquipmentServiceImplTest {
    private EquipmentDao equipmentDao = mock(EquipmentDao.class);
    private EquipmentService serviceUnderTest = new EquipmentServiceImpl(equipmentDao);
    private int equipmentId;
    private Equipment equipment;
    private List<Equipment> equipments;

    @Before
    public void before() throws Exception {
        reset(equipmentDao);
        int size = RandomUtils.randomSmallInteger();
        equipmentId = RandomUtils.randomInteger();
        equipment = InitializationUtils.entity(Equipment.class);
        equipments = InitializationUtils.list(Equipment.class, size);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(equipmentDao);
    }

    @Test
    public void findAllEquipments() {
        // Arrange
        when(equipmentDao.findAll()).thenReturn(equipments);

        // Act
        List<Equipment> result = serviceUnderTest.findAllEquipments();

        // Assert
        assertThat(result).isEqualTo(equipments);
        verify(equipmentDao).findAll();
    }

    @Test
    public void findEquipmentByIdThrowException() throws Exception {
        // Arrange
        when(equipmentDao.findById(equipmentId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.findEquipmentById(equipmentId))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(EQUIPMENT_NOT_FOUND);

        // Assert
        verify(equipmentDao).findById(equipmentId);
    }

    @Test
    public void findEquipmentById() {
        // Arrange
        when(equipmentDao.findById(equipmentId)).thenReturn(equipment);

        // Act
        Equipment result = serviceUnderTest.findEquipmentById(equipmentId);

        // Assert
        assertThat(result).isEqualTo(equipment);
        verify(equipmentDao).findById(equipmentId);
    }

    @Test
    public void createEquipment() {
        // Arrange
        equipment.setId(null);
        when(equipmentDao.create(equipment)).thenReturn(equipment);

        // Act
        serviceUnderTest.createEquipment(equipment);

        // Assert
        ArgumentCaptor<Equipment> equipmentCaptor = ArgumentCaptor.forClass(Equipment.class);
        verify(equipmentDao).create(equipmentCaptor.capture());
        assertThat(equipmentCaptor.getValue()).isEqualTo(equipment);
    }

    @Test
    public void updateEquipment() {
        // Arrange
        equipment.setId(equipmentId);
        when(equipmentDao.create(equipment)).thenReturn(equipment);

        // Act
        serviceUnderTest.updateEquipment(equipmentId, equipment);

        // Assert
        ArgumentCaptor<Equipment> equipmentCaptor = ArgumentCaptor.forClass(Equipment.class);
        verify(equipmentDao).create(equipmentCaptor.capture());
        assertThat(equipmentCaptor.getValue()).isEqualTo(equipment);
    }

    @Test
    public void deleteEquipmentThrowException() throws Exception {
        // Arrange
        when(equipmentDao.findById(equipmentId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.deleteEquipment(equipmentId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(EQUIPMENT_NOT_FOUND);

        // Assert
        verify(equipmentDao).findById(equipmentId);
    }

    @Test
    public void deleteEquipment() {
        // Arrange
        when(equipmentDao.findById(equipmentId)).thenReturn(equipment);

        // Act
        serviceUnderTest.deleteEquipment(equipmentId);

        // Assert
        ArgumentCaptor<Equipment> equipmentCaptor = ArgumentCaptor.forClass(Equipment.class);
        verify(equipmentDao).findById(equipmentId);
        verify(equipmentDao).delete(equipmentCaptor.capture());
        assertThat(equipmentCaptor.getValue()).isEqualTo(equipment);
    }
}
