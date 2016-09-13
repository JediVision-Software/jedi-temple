package com.jedivision.service.impl;

import com.jedivision.configuration.ApplicationDaoMockBeans;
import com.jedivision.dao.JediDao;
import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rank;
import com.jedivision.service.JediService;
import com.jedivision.test.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class JediServiceImplTest {
    @Configuration
    @Import({ApplicationDaoMockBeans.class})
    static class ContextConfiguration {
        // DAO
        @Autowired
        private JediDao jediDao;

        @Bean
        public JediService jediService() {
            return new JediServiceImpl(jediDao);
        }
    }

    // DAO
    @Autowired private JediDao jediDao;

    @Autowired private JediService serviceUnderTest;

    @Before
    public void before() {
        reset(jediDao);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(jediDao);
    }

    @Test
    public void findOne() throws Exception {
        // Arrange
        Integer jediId = RandomUtils.randomInteger();
        Jedi jedi = new Jedi();
        when(jediDao.findOne(jediId)).thenReturn(jedi);

        // Act
        serviceUnderTest.findOne(jediId);

        // Assert
        verify(jediDao).findOne(jediId);
    }

    @Test
    public void findAll() throws Exception {
        // Arrange
        List<Jedi> jedis = Collections.singletonList(new Jedi());
        when(jediDao.findAll()).thenReturn(jedis);

        // Act
        List<Jedi> result = serviceUnderTest.findAll();

        // Assert
        assertThat(result.size(), equalTo(jedis.size()));
        verify(jediDao).findAll();
    }

    @Test
    public void findByRank() throws Exception {
        // Arrange
        Rank rank = RandomUtils.randomEnum(Rank.class);
        List<Jedi> jedis = Collections.singletonList(new Jedi());
        when(jediDao.findByRank(rank)).thenReturn(jedis);

        // Act
        List<Jedi> result = serviceUnderTest.findByRank(rank);

        // Assert
        assertThat(result.size(), equalTo(jedis.size()));
        verify(jediDao).findByRank(rank);
    }

}
