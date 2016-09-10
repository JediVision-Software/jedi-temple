package com.jedivision.service.impl;

import com.jedivision.configuration.ApplicationDaoMockBeans;
import com.jedivision.constants.ApplicationConstants;
import com.jedivision.dao.EquipmentDao;
import com.jedivision.dto.EquipmentAvgRated;
import com.jedivision.entity.Equipment;
import com.jedivision.entity.EquipmentType;
import com.jedivision.entity.Rating;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.EquipmentService;
import com.jedivision.test.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class EquipmentServiceImplTest {
    @Configuration
    @Import({ApplicationDaoMockBeans.class})
    static class ContextConfiguration {
        // DAO
        @Autowired
        private EquipmentDao equipmentDao;

        @Bean
        public EquipmentService equipmentService() {
            return new EquipmentServiceImpl(equipmentDao);
        }
    }

    // DAO
    @Autowired private EquipmentDao equipmentDao;

    @Autowired private EquipmentService serviceUnderTest;

    @Before
    public void before() {
        reset(equipmentDao);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(equipmentDao);
    }

    @Test
    public void rateEquipmentByType() {
        // Arrange
        EquipmentType type = RandomUtils.randomEnum(EquipmentType.class);
        Equipment equipment = new Equipment();
        equipment.setName("Test");
        equipment.setType(type);
        Rating rating = new Rating();
        rating.setRate(99);
        equipment.setRatings(Collections.singletonList(rating));
        List<Equipment> equipments = Collections.singletonList(equipment);
        when(equipmentDao.findAll()).thenReturn(equipments);

        // Act
        EquipmentAvgRated equipmentAvgRated = serviceUnderTest.rateEquipmentByType(type);

        // Assert
        verify(equipmentDao).findAll();
        assertThat(equipmentAvgRated, is(notNullValue()));
        assertThat(equipmentAvgRated.getEquipments().size(), is(equipments.size()));
        assertThat(equipmentAvgRated.getEquipments().get(0).getName(), is(equipment.getName()));
        assertThat(equipmentAvgRated.getEquipments().get(0).getAvgRate(), is(rating.getRate().doubleValue()));
    }

    @Test
    public void rateEquipmentByValue() {
        // Arrange
        Integer value = 5;
        Equipment equipment = new Equipment();
        Equipment equipment1 = new Equipment();
        equipment.setName("Test");
        equipment1.setName("Test");
        equipment.setValue(value);
        equipment1.setValue(3);
        Rating rating = new Rating();
        rating.setRate(99);
        equipment.setRatings(Collections.singletonList(rating));
        equipment1.setRatings(Collections.singletonList(rating));
        List<Equipment> equipments = Arrays.asList(equipment, equipment1);
        when(equipmentDao.findAll()).thenReturn(equipments);

        // Act
        EquipmentAvgRated equipmentAvgRated = serviceUnderTest.rateEquipmentByValue(value);

        // Assert
        verify(equipmentDao).findAll();
        assertThat(equipmentAvgRated, is(notNullValue()));
        assertThat(equipmentAvgRated.getEquipments().get(0).getName(), is(equipment.getName()));
        assertThat(equipmentAvgRated.getEquipments().get(0).getAvgRate(), is(rating.getRate().doubleValue()));
    }

    @Test
    public void findAvgRatingThrowApplicationException() throws Exception {
        // Arrange
        int equipmentId = RandomUtils.randomSmallInteger();
        when(equipmentDao.findOne(equipmentId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.findAvgRating(equipmentId))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(ApplicationConstants.EQUIPMENT_NOT_FOUND);

        // Assert
        verify(equipmentDao).findOne(equipmentId);
    }

    @Test
    public void findAvgRating() throws Exception {
        // Arrange
        int equipmentId = RandomUtils.randomSmallInteger();
        Equipment equipment = new Equipment();
        Rating rating = new Rating();
        rating.setRate(99);
        equipment.setRatings(Collections.singletonList(rating));
        when(equipmentDao.findOne(equipmentId)).thenReturn(equipment);

        // Act
        Double avgRating = serviceUnderTest.findAvgRating(equipmentId);

        // Assert
        verify(equipmentDao).findOne(equipmentId);
        assertThat(avgRating, is(notNullValue()));
        assertThat(avgRating, is(rating.getRate().doubleValue()));
    }
}
