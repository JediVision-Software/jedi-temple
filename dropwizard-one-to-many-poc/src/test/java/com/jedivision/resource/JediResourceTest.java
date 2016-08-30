package com.jedivision.resource;

import com.jedivision.entity.Equipment;
import com.jedivision.entity.Jedi;
import com.jedivision.service.JediService;
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

import static com.jedivision.constants.ApplicationTestConstants.JEDI_BASE_URL;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class JediResourceTest {
    private JediService jediService = mock(JediService.class);
    private int jediId;
    private int equipmentId;
    private Jedi jedi;
    private Equipment equipment;
    private List<Jedi> jedis;

    @Rule
    public ResourceTestRule resource = ResourceTestRule.builder()
            .addResource(new JediResource(jediService))
            .build();

    @Before
    public void before() throws Exception {
        reset(jediService);
        int size = RandomUtils.randomSmallInteger();
        jediId = RandomUtils.randomInteger();
        equipmentId = RandomUtils.randomInteger();
        jedi = InitializationUtils.entity(Jedi.class);
        equipment = InitializationUtils.entity(Equipment.class);
        jedis = InitializationUtils.list(Jedi.class, size);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(jediService);
    }

    @Test
    public void findAll() {
        // Arrange
        when(jediService.findAllJedi()).thenReturn(jedis);

        // Act
        List<Jedi> response = resource.client()
                .target(JEDI_BASE_URL)
                .request(APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Jedi>>() {});

        // Assert
        assertThat(response).isEqualTo(jedis);
        verify(jediService).findAllJedi();
    }

    @Test
    public void findOne() {
        // Arrange
        when(jediService.findJediById(jediId)).thenReturn(jedi);

        // Act
        Jedi response = resource.client()
                .target(JEDI_BASE_URL + jediId)
                .request(APPLICATION_JSON_TYPE)
                .get(new GenericType<Jedi>() {});

        // Assert
        assertThat(response).isEqualTo(jedi);
        verify(jediService).findJediById(jediId);
    }

    @Test
    public void create() {
        // Arrange
        when(jediService.createJedi(jedi)).thenReturn(jedi);

        // Act
        Response response = resource.client()
                .target(JEDI_BASE_URL)
                .request(APPLICATION_JSON_TYPE)
                .post(entity(jedi, APPLICATION_JSON_TYPE));

        // Assert
        ArgumentCaptor<Jedi> jediCaptor = ArgumentCaptor.forClass(Jedi.class);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(jediService).createJedi(jediCaptor.capture());
        assertThat(jediCaptor.getValue()).isEqualTo(jedi);
    }

    @Test
    public void update() {
        // Arrange
        when(jediService.updateJedi(jediId, jedi)).thenReturn(jedi);

        // Act
        Response response = resource.client()
                .target(JEDI_BASE_URL + jediId)
                .request(APPLICATION_JSON_TYPE)
                .put(entity(jedi, APPLICATION_JSON_TYPE));

        // Assert
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(jediService).updateJedi(eq(jediId), eq(jedi));
    }

    @Test
    public void delete() {
        // Act
        Response response = resource.client()
                .target(JEDI_BASE_URL + jediId)
                .request(APPLICATION_JSON_TYPE)
                .delete();

        // Assert
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NO_CONTENT);
        verify(jediService).deleteJedi(jediId);
    }

    @Test
    public void addEquipment() {
        // Arrange
        when(jediService.addEquipmentToJedi(jediId, equipmentId)).thenReturn(equipment);

        // Act
        Response response = resource.client()
                .target(JEDI_BASE_URL + jediId + "/equipment/" + equipmentId)
                .request(APPLICATION_JSON_TYPE)
                .post(entity(null, APPLICATION_JSON_TYPE));

        // Assert
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(jediService).addEquipmentToJedi(eq(jediId), eq(equipmentId));
    }

    @Test
    public void removeEquipments() {
        // Act
        Response response = resource.client()
                .target(JEDI_BASE_URL + jediId + "/equipment/")
                .request(APPLICATION_JSON_TYPE)
                .delete();

        // Assert
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NO_CONTENT);
        verify(jediService).removeEquipmentsFromJedi(jediId);
    }
}
