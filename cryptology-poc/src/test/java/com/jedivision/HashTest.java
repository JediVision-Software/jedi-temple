package com.jedivision;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class HashTest {

    private static final String globalValue = "JDV";
    private static final String globalEncodedMd5Value = "d7df1f0388aa696ce6ca0750afdebda5";

    @Test
    public void md5ViaPureJava() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.md5ViaPureJava(globalValue);

        // Assert
        assertThat(encodedValue, equalTo(globalEncodedMd5Value));
    }

    @Test
    public void md5ViaCommonsCodec() throws NoSuchAlgorithmException {
        // Act
        String encodedValue = Hash.md5ViaCommonsCodec(globalValue);

        // Assert
        assertThat(encodedValue, equalTo(globalEncodedMd5Value));
    }

}
