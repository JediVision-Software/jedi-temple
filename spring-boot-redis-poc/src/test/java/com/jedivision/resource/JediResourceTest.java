package com.jedivision.resource;

import com.jedivision.entity.Jedi;
import com.jedivision.service.JediService;
import com.jedivision.test.runner.ApplicationResourceRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.jedivision.test.RandomUtils.randomString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JediResourceTest extends ApplicationResourceRunner {

    @Autowired
    private JediService jediService;

    @Autowired
    private JediResource resourceUnderTest;

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
    public void put() throws Exception {
        // Arrange
        String key = randomString();

        // Act + Assert
        mvc.perform(get("/jedi/put/" + key).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).put(eq(key), any(Jedi.class));
    }

    @Test
    public void getValue() throws Exception {
        // Arrange
        String key = randomString();

        // Act + Assert
        mvc.perform(get("/jedi/get/" + key).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).get(key);
    }


}
