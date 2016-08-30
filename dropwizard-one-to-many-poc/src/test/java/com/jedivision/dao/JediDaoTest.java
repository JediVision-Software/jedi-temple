package com.jedivision.dao;

import com.jedivision.entity.Jedi;
import com.jedivision.test.InitializationUtils;
import com.jedivision.test.runner.ApplicationDaoRunner;
import org.junit.Test;

import java.util.List;

import static com.jedivision.constants.ApplicationTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class JediDaoTest extends ApplicationDaoRunner {

    private JediDao daoUndetTest = new JediDao(getSession().getSessionFactory());

    @Test
    public void findAll() {
        // Act
        List<Jedi> result = daoUndetTest.findAll();

        // Assert
        assertThat(result).hasSize(JEDIS_NUMBER);
    }

    @Test
    public void findByIdJediIsMissing() {
        // Act
        Jedi result = daoUndetTest.findById(MISSING_JEDI);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    public void findById() {
        // Act
        Jedi result = daoUndetTest.findById(PRESENT_JEDI);

        // Assert
        assertThat(result.getName()).isEqualTo(JEDI_NAME);
        assertThat(result.getRank()).isEqualTo(JEDI_RANK);
    }

    @Test
    public void create() {
        // Arrange
        Jedi jedi = InitializationUtils.entity(Jedi.class);

        // Act
        Jedi result = daoUndetTest.create(jedi);

        // Assert
        assertThat(jedi).isNotNull();
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(jedi.getName());
        assertThat(result.getRank()).isEqualTo(jedi.getRank());
    }

    @Test
    public void delete() {
        // Arrange
        Jedi jedi = daoUndetTest.findById(PRESENT_JEDI);

        // Act
        daoUndetTest.delete(jedi);
        Jedi result = daoUndetTest.findById(PRESENT_JEDI);

        // Assert
        assertThat(result).isNull();
    }
}
