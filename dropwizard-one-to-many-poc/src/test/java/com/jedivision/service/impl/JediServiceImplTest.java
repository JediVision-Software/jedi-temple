package com.jedivision.service.impl;

import com.jedivision.dao.EquipmentDao;
import com.jedivision.dao.JediDao;
import com.jedivision.entity.Equipment;
import com.jedivision.entity.Jedi;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.JediService;
import com.jedivision.test.InitializationUtils;
import com.jedivision.test.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static com.jedivision.constants.ApplicationConstants.JEDI_NOT_FOUND;
import static com.jedivision.constants.ApplicationConstants.JEDI_OR_EQUIPMENT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class JediServiceImplTest {
    private JediDao jediDao = mock(JediDao.class);
    private EquipmentDao equipmentDao = mock(EquipmentDao.class);
    private JediService serviceUnderTest = new JediServiceImpl(jediDao, equipmentDao);
    private int jediId;
    private int equipmentId;
    private Jedi jedi;
    private Equipment equipment;
    private List<Jedi> jedis;

    @Before
    public void before() throws Exception {
        reset(jediDao, equipmentDao);
        int size = RandomUtils.randomSmallInteger();
        jediId = RandomUtils.randomInteger();
        equipmentId = RandomUtils.randomInteger();
        jedi = InitializationUtils.entity(Jedi.class);
        equipment = InitializationUtils.entity(Equipment.class);
        jedis = InitializationUtils.list(Jedi.class, size);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(jediDao, equipmentDao);
    }

    @Test
    public void index() {
        // Arrange
        when(jediDao.create(any(Jedi.class))).thenReturn(jedi);
        when(equipmentDao.create(any(Equipment.class))).thenReturn(equipment);
        when(equipmentDao.findById(any(Integer.class))).thenReturn(equipment);
        when(jediDao.findById(any(Integer.class))).thenReturn(jedi);

        // Act
        serviceUnderTest.indexJedi();

        // Assert
        verify(jediDao, times(2)).create(any(Jedi.class));
        verify(jediDao, times(4)).findById(any(Integer.class));
        verify(equipmentDao, times(8)).create(any(Equipment.class));
        verify(equipmentDao, times(4)).findById(any(Integer.class));
    }

    @Test
    public void findAllJedi() {
        // Arrange
        when(jediDao.findAll()).thenReturn(jedis);

        // Act
        List<Jedi> result = serviceUnderTest.findAllJedi();

        // Assert
        assertThat(result).isEqualTo(jedis);
        verify(jediDao).findAll();
    }

    @Test
    public void findJediByIdThrowException() throws Exception {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.findJediById(jediId))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(JEDI_NOT_FOUND);

        // Assert
        verify(jediDao).findById(jediId);
    }

    @Test
    public void findJediById() {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(jedi);

        // Act
        Jedi result = serviceUnderTest.findJediById(jediId);

        // Assert
        assertThat(result).isEqualTo(jedi);
        verify(jediDao).findById(jediId);
    }

    @Test
    public void createJedi() {
        // Arrange
        jedi.setId(null);
        when(jediDao.create(jedi)).thenReturn(jedi);

        // Act
        serviceUnderTest.createJedi(jedi);

        // Assert
        ArgumentCaptor<Jedi> jediCaptor = ArgumentCaptor.forClass(Jedi.class);
        verify(jediDao).create(jediCaptor.capture());
        assertThat(jediCaptor.getValue()).isEqualTo(jedi);
    }

    @Test
    public void updateJedi() {
        // Arrange
        jedi.setId(jediId);
        when(jediDao.create(jedi)).thenReturn(jedi);

        // Act
        serviceUnderTest.updateJedi(jediId, jedi);

        // Assert
        ArgumentCaptor<Jedi> jediCaptor = ArgumentCaptor.forClass(Jedi.class);
        verify(jediDao).create(jediCaptor.capture());
        assertThat(jediCaptor.getValue()).isEqualTo(jedi);
    }

    @Test
    public void deleteJediThrowException() throws Exception {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.deleteJedi(jediId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(JEDI_NOT_FOUND);

        // Assert
        verify(jediDao).findById(jediId);
    }

    @Test
    public void deleteJedi() {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(jedi);

        // Act
        serviceUnderTest.deleteJedi(jediId);

        // Assert
        ArgumentCaptor<Jedi> jediCaptor = ArgumentCaptor.forClass(Jedi.class);
        verify(jediDao).findById(jediId);
        verify(jediDao).delete(jediCaptor.capture());
        assertThat(jediCaptor.getValue()).isEqualTo(jedi);
    }

    @Test
    public void addEquipmentToJediJediNotFound() {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.addEquipmentToJedi(jediId, equipmentId))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(JEDI_OR_EQUIPMENT_NOT_FOUND);

        // Assert
        verify(jediDao).findById(jediId);
        verify(equipmentDao).findById(equipmentId);
    }

    @Test
    public void addEquipmentToJediEquipmentNotFound() {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(jedi);
        when(equipmentDao.findById(equipmentId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.addEquipmentToJedi(jediId, equipmentId))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(JEDI_OR_EQUIPMENT_NOT_FOUND);

        // Assert
        verify(jediDao).findById(jediId);
        verify(equipmentDao).findById(equipmentId);
    }

    @Test
    public void addEquipmentToJedi() {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(jedi);
        when(equipmentDao.findById(equipmentId)).thenReturn(equipment);
        equipment.setJedi(jedi);
        when(equipmentDao.create(equipment)).thenReturn(equipment);

        // Act
        serviceUnderTest.addEquipmentToJedi(jediId, equipmentId);

        // Assert
        ArgumentCaptor<Equipment> equipmentCaptor = ArgumentCaptor.forClass(Equipment.class);
        verify(jediDao).findById(jediId);
        verify(equipmentDao).findById(equipmentId);
        verify(equipmentDao).create(equipmentCaptor.capture());
        assertThat(equipmentCaptor.getValue()).isEqualTo(equipment);
    }

    @Test
    public void removeEquipmentsFromJediThrowException() {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.removeEquipmentsFromJedi(jediId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(JEDI_NOT_FOUND);

        // Assert
        verify(jediDao).findById(jediId);
    }

    @Test
    public void removeEquipmentsFromJedi() {
        // Arrange
        when(jediDao.findById(jediId)).thenReturn(jedi);

        // Act
        serviceUnderTest.removeEquipmentsFromJedi(jediId);

        // Assert
        verify(jediDao).findById(jediId);
        verify(equipmentDao, times(jedi.getEquipments().size())).delete(any(Equipment.class));
    }
}
