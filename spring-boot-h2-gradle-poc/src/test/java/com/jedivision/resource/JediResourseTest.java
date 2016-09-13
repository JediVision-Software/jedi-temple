package com.jedivision.resource;

import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rank;
import com.jedivision.service.JediService;
import com.jedivision.test.RandomUtils;
import com.jedivision.test.runner.ApplicationResourceRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JediResourseTest extends ApplicationResourceRunner {
    // Service
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
    public void findById() throws Exception {
        // Arrange
        int jediId = RandomUtils.randomInteger();
        Jedi jedi = new Jedi();
        when(jediService.findOne(jediId)).thenReturn(jedi);

        // Act + Assert
        mvc.perform(get("/jedi/" + jediId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findOne(jediId);
    }

    @Test
    public void findAll() throws Exception {
        // Arrange
        Jedi jedi = new Jedi();
        Jedi jed2 = new Jedi();
        List<Jedi> jedis = Arrays.asList(jedi, jed2);
        when(jediService.findAll()).thenReturn(jedis);

        // Act + Assert
        mvc.perform(get("/jedi").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findAll();
    }

    @Test
    public void findByRank() throws Exception {
        // Arrange
        Rank rank = RandomUtils.randomEnum(Rank.class);
        Jedi jedi = new Jedi();
        Jedi jed2 = new Jedi();
        List<Jedi> jedis = Arrays.asList(jedi, jed2);
        when(jediService.findByRank(rank)).thenReturn(jedis);

        // Act + Assert
        mvc.perform(get("/jedi/search?rank=" + rank).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findByRank(rank);
    }
}
