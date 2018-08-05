package com.jedivision.resource;

import com.jedivision.document.Rank;
import com.jedivision.service.JediService;
import com.jedivision.test.runner.ApplicationResourceRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.jedivision.test.RandomUtils.*;
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
    public void index() throws Exception {
        // Act + Assert
        mvc.perform(get("/elastic/jedi/index").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).index();
        verify(jediService).saveJedis();
    }

    @Test
    public void findAll() throws Exception {
        // Act + Assert
        mvc.perform(get("/elastic/jedi/findAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findAll();
    }

    @Test
    public void findAllSortedByForceByAge() throws Exception {
        // Act + Assert
        mvc.perform(get("/elastic/jedi/findAll/sorted").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findAllSortedByForceByAge();
    }

    @Test
    public void findByForceBetween() throws Exception {
        // Arrange
        Long begin = randomLong();
        Long end = randomLong();

        // Act + Assert
        mvc.perform(get("/elastic/jedi/findByForceBetween/" + begin + "/" + end).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findByForceBetween(eq(begin), eq(end));
    }

    @Test
    public void findByRankOrderByAge() throws Exception {
        // Arrange
        Rank rank = randomEnum(Rank.class);

        // Act + Assert
        mvc.perform(get("/elastic/jedi/findByRankOrderByAge/" + rank).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findByRankOrderByAge(eq(rank));
    }

    @Test
    public void findByForceGreaterThanAndRankIs() throws Exception {
        // Arrange
        Long force = randomLong();
        Rank rank = randomEnum(Rank.class);

        // Act + Assert
        mvc.perform(get("/elastic/jedi/findByForceGreaterThanAndRankIs/" + force + "/" + rank).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findByForceGreaterThanAndRankIs(eq(force), eq(rank));
    }

    @Test
    public void findNear() throws Exception {
        // Arrange
        Double latitude = randomDouble();
        Double longitude = randomDouble();
        String distance = randomString();

        // Act + Assert
        mvc.perform(get("/elastic/jedi/findNear/" + latitude + "/" + longitude + "/" + distance).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).findNear(eq(latitude), eq(longitude), eq(distance));
    }

    @Test
    public void countByForceLessThan() throws Exception {
        // Arrange
        Long force = randomLong();

        // Act + Assert
        mvc.perform(get("/elastic/jedi/countByForceLessThan/" + force).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        verify(jediService).countByForceLessThan(eq(force));
    }
}
