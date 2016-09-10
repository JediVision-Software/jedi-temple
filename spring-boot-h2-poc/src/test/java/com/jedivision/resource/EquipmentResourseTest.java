package com.jedivision.resource;

import com.jedivision.dto.EquipmentAvgRated;
import com.jedivision.entity.EquipmentType;
import com.jedivision.service.EquipmentService;
import com.jedivision.test.InitializationUtils;
import com.jedivision.test.RandomUtils;
import com.jedivision.test.runner.ApplicationResourceRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EquipmentResourseTest extends ApplicationResourceRunner {
    // Service
    @Autowired
    private EquipmentService equipmentService;

    @Autowired private EquipmentResource resourceUnderTest;

    @Before
    public void before() throws Exception {
        beforeByResource(resourceUnderTest);
        reset(equipmentService);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(equipmentService);
    }

    @Test
    public void rateEquipmentByType() throws Exception {
        // Arrange
        EquipmentType type = RandomUtils.randomEnum(EquipmentType.class);
        EquipmentAvgRated equipmentAvgRated = InitializationUtils.entity(EquipmentAvgRated.class);
        when(equipmentService.rateEquipmentByType(type)).thenReturn(equipmentAvgRated);

        // Act + Assert
        mvc.perform(get("/equipment/type/" + type).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(equipmentService).rateEquipmentByType(type);
    }

    @Test
    public void rateEquipmentByValue() throws Exception {
        // Arrange
        Integer value = RandomUtils.randomInteger();
        EquipmentAvgRated equipmentAvgRated = InitializationUtils.entity(EquipmentAvgRated.class);
        when(equipmentService.rateEquipmentByValue(value)).thenReturn(equipmentAvgRated);

        // Act + Assert
        mvc.perform(get("/equipment/minValue/" + value).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(equipmentService).rateEquipmentByValue(value);
    }

    @Test
    public void avgEquipmentRating() throws Exception {
        // Arrange
        Integer equipmentId = RandomUtils.randomInteger();
        Double avg = RandomUtils.randomDouble();
        when(equipmentService.findAvgRating(equipmentId)).thenReturn(avg);

        // Act + Assert
        mvc.perform(get("/equipment/" + equipmentId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(equipmentService).findAvgRating(equipmentId);
    }
}
