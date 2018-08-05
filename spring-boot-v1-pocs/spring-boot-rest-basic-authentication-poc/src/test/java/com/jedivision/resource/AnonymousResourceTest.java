package com.jedivision.resource;

import com.jedivision.test.AbstractResourceRunner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnonymousResourceTest extends AbstractResourceRunner {

    @Autowired private AnonymousResource resourceUnderTest;

    @Before
    public void before() {
        setUp(resourceUnderTest);
    }

    @Test
    public void getVersion() throws Exception {
        // Act + Assert
        mvc.perform(get("/api/version"))
                .andExpect(status().isOk());
    }
}
