package com.jedivision.service.impl;

import com.jedivision.entity.Jedi;
import com.jedivision.service.JediService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static com.jedivision.test.RandomUtils.randomString;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class JediServiceImplTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        RedisTemplate<String, Jedi> redisTemplate() {
            return (RedisTemplate<String, Jedi>)mock(RedisTemplate.class);
        }

        @Bean
        JediService jediService() {
            return new JediServiceImpl(redisTemplate());
        }
    }

    @Autowired
    private RedisTemplate template;

    @Autowired
    private JediService serviceUnderTest;

    @Before
    public void before() throws Exception {
        reset(template);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(template);
    }

    @Test
    public void putKeyIsNotPresent() {
        // Arrange
        String key = randomString();
        String fullName = randomString();
        ValueOperations ops = mock(ValueOperations.class);
        Jedi jedi = Jedi.builder()
                .fullName(fullName)
                .build();
        when(template.hasKey(key)).thenReturn(false);
        when(template.opsForValue()).thenReturn(ops);

        // Act
        serviceUnderTest.put(key, jedi);

        // Assert
        verify(template).hasKey(key);
        verify(template).opsForValue();
    }

    @Test
    public void putKeyIsPresent() {
        // Arrange
        String key = randomString();
        String fullName = randomString();
        ValueOperations ops = mock(ValueOperations.class);
        Jedi jedi = Jedi.builder()
                .fullName(fullName)
                .build();
        when(template.hasKey(key)).thenReturn(true);
        when(template.opsForValue()).thenReturn(ops);

        // Act
        serviceUnderTest.put(key, jedi);

        // Assert
        verify(template).opsForValue();
        verify(template).hasKey(key);
    }

    @Test
    public void get() {
        // Arrange
        String key = randomString();
        ValueOperations ops = mock(ValueOperations.class);
        when(template.opsForValue()).thenReturn(ops);

        // Act
        serviceUnderTest.get(key);

        // Assert
        verify(template).opsForValue();
        Assert.assertTrue(true);
    }
}
