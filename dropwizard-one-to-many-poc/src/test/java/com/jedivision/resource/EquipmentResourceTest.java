package com.jedivision.resource;

import com.jedivision.entity.Equipment;
import com.jedivision.service.EquipmentService;
import com.jedivision.test.InitializationUtils;
import com.jedivision.test.RandomUtils;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.jedivision.constants.ApplicationTestConstants.EQUIPMENT_BASE_URL;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EquipmentResourceTest {
    private EquipmentService equipmentService = mock(EquipmentService.class);
    private int equipmentId;
    private Equipment equipment;
    private List<Equipment> equipments;

    @Rule
    public ResourceTestRule resource = ResourceTestRule.builder()
            .addResource(new EquipmentResource(equipmentService))
            .build();

    @Before
    public void before() throws Exception {
        reset(equipmentService);
        int size = RandomUtils.randomSmallInteger();
        equipmentId = RandomUtils.randomInteger();
        equipment = InitializationUtils.entity(Equipment.class);
        equipments = InitializationUtils.list(Equipment.class, size);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(equipmentService);
    }

    @Test
    public void findAll() {
        // Arrange
        when(equipmentService.findAllEquipments()).thenReturn(equipments);

        // Act
        List<Equipment> response = resource.client()
                .target(EQUIPMENT_BASE_URL)
                .request(APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Equipment>>() {});

        // Assert
        assertThat(response).isEqualTo(equipments);
        verify(equipmentService).findAllEquipments();
    }

    @Test
    public void findOne() {
        // Arrange
        when(equipmentService.findEquipmentById(equipmentId)).thenReturn(equipment);

        // Act
        Equipment response = resource.client()
                .target(EQUIPMENT_BASE_URL + equipmentId)
                .request(APPLICATION_JSON_TYPE)
                .get(new GenericType<Equipment>() {});

        // Assert
        assertThat(response).isEqualTo(equipment);
        verify(equipmentService).findEquipmentById(equipmentId);
    }

    @Test
    public void create() {
        // Arrange
        when(equipmentService.createEquipment(equipment)).thenReturn(equipment);

        // Act
        Response response = resource.client()
                .target(EQUIPMENT_BASE_URL)
                .request(APPLICATION_JSON_TYPE)
                .post(entity(equipment, APPLICATION_JSON_TYPE));

        // Assert
        ArgumentCaptor<Equipment> equipmentCaptor = ArgumentCaptor.forClass(Equipment.class);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(equipmentService).createEquipment(equipmentCaptor.capture());
        assertThat(equipmentCaptor.getValue()).isEqualTo(equipment);
    }

    @Test
    public void update() {
        // Arrange
        when(equipmentService.updateEquipment(equipmentId, equipment)).thenReturn(equipment);

        // Act
        Response response = resource.client()
                .target(EQUIPMENT_BASE_URL + equipmentId)
                .request(APPLICATION_JSON_TYPE)
                .put(entity(equipment, APPLICATION_JSON_TYPE));

        // Assert
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(equipmentService).updateEquipment(eq(equipmentId), eq(equipment));
    }

    @Test
    public void delete() {
        // Act
        Response response = resource.client()
                .target(EQUIPMENT_BASE_URL + equipmentId)
                .request(APPLICATION_JSON_TYPE)
                .delete();

        // Assert
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NO_CONTENT);
        verify(equipmentService).deleteEquipment(equipmentId);
    }
}
