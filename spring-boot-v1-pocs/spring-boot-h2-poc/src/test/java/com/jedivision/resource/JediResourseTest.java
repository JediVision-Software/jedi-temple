package com.jedivision.resource;

import com.jedivision.dto.EquipmentRatedForJedi;
import com.jedivision.entity.EquipmentType;
import com.jedivision.service.JediService;
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

public class JediResourseTest extends ApplicationResourceRunner {
    // Service
    @Autowired
    private JediService jediService;

    @Autowired private JediResource resourceUnderTest;

    @Before
    public void before() throws Exception {
        beforeByResource(resourceUnderTest);
        reset(jediService);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(jediService);
    }

    @Test
    public void equipmentRatedForJedi() throws Exception {
        // Arrange
        Integer jediId = RandomUtils.randomInteger();
        EquipmentRatedForJedi equipmentRatedForJedi = InitializationUtils.entity(EquipmentRatedForJedi.class);
        when(jediService.rateEquipmentForJedi(jediId)).thenReturn(equipmentRatedForJedi);

        // Act + Assert
        mvc.perform(get("/jedi/" + jediId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).rateEquipmentForJedi(jediId);
    }

    @Test
    public void equipmentRatedByTypeForJedi() throws Exception {
        // Arrange
        Integer jediId = RandomUtils.randomInteger();
        EquipmentType type = RandomUtils.randomEnum(EquipmentType.class);
        EquipmentRatedForJedi equipmentRatedForJedi = InitializationUtils.entity(EquipmentRatedForJedi.class);
        when(jediService.rateEquipmentByTypeForJedi(jediId, type)).thenReturn(equipmentRatedForJedi);

        // Act + Assert
        mvc.perform(get("/jedi/" + jediId + "/type/" + type).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).rateEquipmentByTypeForJedi(jediId, type);
    }
}
