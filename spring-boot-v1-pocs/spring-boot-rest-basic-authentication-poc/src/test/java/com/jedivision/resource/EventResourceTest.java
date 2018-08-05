package com.jedivision.resource;

import com.jedivision.test.AbstractResourceRunner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jedivision.configuration.ApplicationResourceBeans.*;
import static com.jedivision.test.RandomUtils.randomInteger;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventResourceTest extends AbstractResourceRunner {

    @Autowired private EventResource resourceUnderTest;

    @Before
    public void before() {
        setUp(resourceUnderTest);
    }

    @Test
    public void findAllUnauthorized() throws Exception {
        // Act + Assert
        mvc.perform(get("/api/event"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void findAllAdminRole() throws Exception {
        // Act + Assert
        mvc.perform(get("/api/event")
                .with(user(ADMIN_USERNAME).password(ADMIN_PASSWORD).roles(ADMIN_ROLE)))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUserRole() throws Exception {
        // Act + Assert
        mvc.perform(get("/api/event")
                .with(user(USER_USERNAME).password(USER_PASSWORD).roles(USER_ROLE)))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdUnauthorized() throws Exception {
        Integer eventId = randomInteger();

        // Act + Assert
        mvc.perform(get("/api/event/" + eventId))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void findByIdAdminRole() throws Exception {
        Integer eventId = randomInteger();

        // Act + Assert
        mvc.perform(get("/api/event/" + eventId)
                .with(user(ADMIN_USERNAME).password(ADMIN_PASSWORD).roles(ADMIN_ROLE)))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdUserRole() throws Exception {
        Integer eventId = randomInteger();

        // Act + Assert
        mvc.perform(get("/api/event/" + eventId)
                .with(user(USER_USERNAME).password(USER_PASSWORD).roles(USER_ROLE)))
                .andExpect(status().isForbidden());
    }
}
