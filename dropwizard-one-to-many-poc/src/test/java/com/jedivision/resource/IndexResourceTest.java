package com.jedivision.resource;

import com.jedivision.service.JediService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static com.jedivision.constants.ApplicationTestConstants.INDEX_BASE_URL;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class IndexResourceTest {
    private JediService jediService = mock(JediService.class);

    @Rule
    public ResourceTestRule resource = ResourceTestRule.builder()
            .addResource(new IndexResource(jediService))
            .build();

    @Before
    public void before() throws Exception {
        reset(jediService);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(jediService);
    }

    @Test
    public void index() {
        // Act
        Response response = resource.client()
                .target(INDEX_BASE_URL)
                .request(APPLICATION_JSON_TYPE)
                .get();

        // Assert
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(jediService).indexJedi();
    }

}
