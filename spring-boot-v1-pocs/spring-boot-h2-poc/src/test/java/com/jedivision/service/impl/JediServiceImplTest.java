package com.jedivision.service.impl;

import com.jedivision.configuration.ApplicationDaoMockBeans;
import com.jedivision.constants.ApplicationConstants;
import com.jedivision.dao.JediDao;
import com.jedivision.dto.EquipmentRatedForJedi;
import com.jedivision.entity.Equipment;
import com.jedivision.entity.EquipmentType;
import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rating;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.JediService;
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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class JediServiceImplTest {
    @Configuration
    @Import({ApplicationDaoMockBeans.class})
    static class ContextConfiguration {
        // DAO
        @Autowired
        private JediDao jediDao;

        @Bean
        public JediService jediService() {
            return new JediServiceImpl(jediDao);
        }
    }

    // DAO
    @Autowired private JediDao jediDao;

    @Autowired private JediService serviceUnderTest;

    @Before
    public void before() {
        reset(jediDao);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(jediDao);
    }

    @Test
    public void rateEquipmentForJediThrowApplicationException() throws Exception {
        // Arrange
        int jediId = RandomUtils.randomSmallInteger();
        when(jediDao.findOne(jediId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.rateEquipmentForJedi(jediId))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(ApplicationConstants.JEDI_NOT_FOUND);

        // Assert
        verify(jediDao).findOne(jediId);
    }

    @Test
    public void rateEquipmentForJedi() throws Exception {
        // Arrange
        int jediId = RandomUtils.randomSmallInteger();
        Jedi jedi = new Jedi();
        Rating rating = new Rating();
        Equipment equipment = new Equipment();
        rating.setRate(99);
        rating.setEquipment(equipment);
        jedi.setRatings(Collections.singletonList(rating));
        when(jediDao.findOne(jediId)).thenReturn(jedi);

        // Act
        EquipmentRatedForJedi equipmentRatedForJedi = serviceUnderTest.rateEquipmentForJedi(jediId);

        // Assert
        verify(jediDao).findOne(jediId);
        assertThat(equipmentRatedForJedi, is(notNullValue()));
        assertThat(equipmentRatedForJedi.getTotalCount(), is(jedi.getRatings().size()));
        assertThat(equipmentRatedForJedi.getEquipments().get(0).getRate(), is(rating.getRate()));
    }

    @Test
    public void rateEquipmentByTypeForJediThrowApplicationException() throws Exception {
        // Arrange
        int jediId = RandomUtils.randomSmallInteger();
        EquipmentType type = RandomUtils.randomEnum(EquipmentType.class);
        when(jediDao.findOne(jediId)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.rateEquipmentByTypeForJedi(jediId, type))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(ApplicationConstants.JEDI_NOT_FOUND);

        // Assert
        verify(jediDao).findOne(jediId);
    }

    @Test
    public void rateEquipmentByTypeForJedi() throws Exception {
        // Arrange
        int jediId = RandomUtils.randomSmallInteger();
        EquipmentType type = RandomUtils.randomEnum(EquipmentType.class);
        Jedi jedi = new Jedi();
        Rating rating = new Rating();
        Equipment equipment = new Equipment();
        rating.setRate(99);
        equipment.setType(type);
        rating.setEquipment(equipment);
        jedi.setRatings(Collections.singletonList(rating));
        when(jediDao.findOne(jediId)).thenReturn(jedi);

        // Act
        EquipmentRatedForJedi equipmentRatedForJedi = serviceUnderTest.rateEquipmentByTypeForJedi(jediId, type);

        // Assert
        verify(jediDao).findOne(jediId);
        assertThat(equipmentRatedForJedi, is(notNullValue()));
        assertThat(equipmentRatedForJedi.getTotalCount(), is(1));
        assertThat(equipmentRatedForJedi.getEquipments().size(), is(1));
    }
}
