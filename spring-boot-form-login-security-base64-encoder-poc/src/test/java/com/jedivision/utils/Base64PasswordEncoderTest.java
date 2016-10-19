package com.jedivision.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static com.jedivision.test.RandomUtils.randomString;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class Base64PasswordEncoderTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        Base64PasswordEncoder base64PasswordEncoder() {
            return new Base64PasswordEncoder();
        }

    }
    private final static String PASSWORD = "admin";
    private final static String ENCODED_PASSWORD = "YWRtaW4=";

    @Autowired private Base64PasswordEncoder encoderUnderTest ;

    @Test
    public void encode() {
        // Act
        String result = encoderUnderTest.encode(PASSWORD);

        // Assert
        assertEquals(result, ENCODED_PASSWORD);

    }

    @Test
    public void notMatches() {
        // Arrange
        String encodedPassword = randomString();

        // Act
        Boolean result = encoderUnderTest.matches(PASSWORD, encodedPassword);

        // Assert
        assertFalse(result);

    }

    @Test
    public void matches() {
        // Act
        Boolean result = encoderUnderTest.matches(PASSWORD, ENCODED_PASSWORD);

        // Assert
        assertTrue(result);

    }
}
