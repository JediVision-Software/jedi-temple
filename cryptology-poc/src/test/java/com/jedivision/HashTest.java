package com.jedivision;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class HashTest {

    @Test
    public void md5() throws NoSuchAlgorithmException {
        // Arrange
        String value = "JDV";

        // Act
        String encodedValue = Hash.md5(value);

        // Assert
        assertEquals("d7df1f0388aa696ce6ca0750afdebda5", encodedValue);
    }
}
